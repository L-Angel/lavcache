package com.langel.lavcache.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public class PackageScanner {

    private static final Logger LOGGER = LoggerFactory.getLogger(PackageScanner.class);

    public static Set<Class<?>> scan(String basePackage, Class<? extends Annotation> anno) {
        return scan(basePackage).stream()
                .filter(v -> v.getAnnotation(anno) != null)
                .collect(Collectors.toSet());
    }

    public static Set<Class<?>> scan(String basePackage) {
        Set<Class<?>> classes = new HashSet<>();
        String basePackgaeDirName = basePackage.replace(".", "/");
        Enumeration<URL> dirs;
        boolean recursive = true;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(basePackgaeDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if ("file".equalsIgnoreCase(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findAndAddClassesInPackageByFile(basePackage, filePath, recursive, classes);
                } else if ("jar".equalsIgnoreCase(protocol)) {
                    JarFile jar;
                    jar = ((JarURLConnection) url.openConnection()).getJarFile();
                    Enumeration<JarEntry> entries = jar.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        String name = entry.getName();
                        if (name.startsWith("/")) {
                            name = name.substring(1);
                        }
                        if (name.startsWith(basePackgaeDirName)) {
                            int idx = name.lastIndexOf("/");
                            if (idx != -1) {
                                basePackage = name.substring(0, idx).replace("/", ".");
                                if (recursive && name.endsWith(".class") && !entry.isDirectory()) {
                                    String className = name.substring(basePackage.length() + 1, name.length() - 6);
                                    try {
                                        classes.add(Class.forName(basePackage + "." + className));
                                    } catch (ClassNotFoundException e) {
                                        LOGGER.error("Scan Class Error : " + e.getMessage(), e);
                                    }
                                }

                            }
                        }
                    }
                }
            }
            return classes;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.EMPTY_SET;
        }
    }

    private static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes) {
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] dirFiles = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (recursive && file.isDirectory()) || file.getName().endsWith(".class");
            }
        });
        if (dirFiles == null) {
            return;
        }
        for (File f : dirFiles) {
            if (f.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + f.getName(), f.getAbsolutePath(), recursive, classes);
            } else {
                String className = f.getName().substring(0, f.getName().length() - 6);
                try {
                    classes.add(Class.forName(packageName + "." + className));
                } catch (ClassNotFoundException e) {
                    LOGGER.error("Scan Class Error : " + e.getMessage());
                }
            }
        }
    }
}
