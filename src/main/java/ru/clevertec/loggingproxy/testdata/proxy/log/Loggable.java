package ru.clevertec.loggingproxy.testdata.proxy.log;

public interface Loggable {

    Object invokeAndLog(Object original, String simpleName, String methodName, Object... args);

}
