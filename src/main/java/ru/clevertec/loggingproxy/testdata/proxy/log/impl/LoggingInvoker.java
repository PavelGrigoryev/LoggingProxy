package ru.clevertec.loggingproxy.testdata.proxy.log.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ru.clevertec.loggingproxy.myspring.annotation.Log;
import ru.clevertec.loggingproxy.myspring.annotation.Singleton;
import ru.clevertec.loggingproxy.testdata.proxy.log.Loggable;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@Singleton
public class LoggingInvoker implements Loggable {

    @Override
    @SneakyThrows
    public Object invokeAndLog(Object original, String simpleName, String methodName, Object... args) {
        Method method = original.getClass().getMethod(methodName, Arrays.stream(args)
                .map(Object::getClass)
                .toArray(Class[]::new));
        if (method.isAnnotationPresent(Log.class)) {
            log.warn("Starting : {}.{}", simpleName, method.getName());
            Object result = method.invoke(original, args);
            log.info(result.toString());
            log.warn("Finishing : {}.{}", simpleName, method.getName());
            return result;
        }
        return method.invoke(original, args);
    }

}
