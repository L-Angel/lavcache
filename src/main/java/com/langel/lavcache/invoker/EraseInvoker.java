package com.langel.lavcache.invoker;

import com.langel.lavcache.piece.EraseHolder;
import com.langel.lavcache.sector.Sector;
import com.langel.lavcache.util.KeyGenerator;

import java.lang.reflect.Method;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/27 上午11:55
 **/
public class EraseInvoker implements Invoker {

    private final KeyGenerator keyGenerator = new KeyGenerator();

    @Override
    public Object invoke(Invocation invocation) {
        Method m = invocation.method();
        EraseHolder holder = EraseHolder.Builder.build(m);
        String key = keyGenerator.generate(holder, invocation.args());
        Sector sector = holder.sector();
        return sector.removeRaw(key,holder);
    }
}
