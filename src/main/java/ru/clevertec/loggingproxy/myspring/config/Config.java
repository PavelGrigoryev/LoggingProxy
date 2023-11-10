package ru.clevertec.loggingproxy.myspring.config;

import org.reflections.Reflections;

public interface Config {

    <T> Class<?> getImplClass(Class<T> ifc);

    Reflections getScanner();

}
