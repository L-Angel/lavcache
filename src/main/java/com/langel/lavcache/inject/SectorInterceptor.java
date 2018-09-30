package com.langel.lavcache.inject;

import com.langel.lavcache.annotation.Erase;
import com.langel.lavcache.annotation.Piece;
import com.langel.lavcache.invoker.EraseInvoker;
import com.langel.lavcache.invoker.Invocation;
import com.langel.lavcache.invoker.PieceInvoker;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/25
 **/
public class SectorInterceptor implements MethodInterceptor {

    private final PieceInvoker pieceInvoker = new PieceInvoker();

    private final EraseInvoker eraseInvoker = new EraseInvoker();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method m = invocation.getMethod();
        Invocation iv = Invocation.Builder.build(invocation.getArguments(), m, invocation);

        Optional<Annotation> anno = lavOp(m.getAnnotations());

        if (!anno.isPresent()) {
            return invocation.proceed();
        }
        if (anno.get() instanceof Piece) {
            return pieceInvoker.invoke(iv);
        } else if (anno.get() instanceof Erase) {
            return eraseInvoker.invoke(iv);
        }
        return invocation.proceed();

    }

    private Optional<Annotation> lavOp(Annotation[] annos) {
        for (Annotation anno : annos) {
            if ((anno instanceof Piece) || (anno instanceof Erase)) {
                return Optional.of(anno);
            }
        }
        return Optional.empty();
    }


}
