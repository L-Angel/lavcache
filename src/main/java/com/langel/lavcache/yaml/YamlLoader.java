package com.langel.lavcache.yaml;

import java.io.InputStream;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/28 2:40 PM
 **/
public class YamlLoader {


    public static <T> T load(String filename, Class<T> clazz) {
        org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();
        return yaml.loadAs(read(filename), clazz);
    }

    private static InputStream read(String filename) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
    }

}
