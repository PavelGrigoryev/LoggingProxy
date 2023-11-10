package ru.clevertec.loggingproxy.myspring.config.impl;

import lombok.Getter;
import org.reflections.Reflections;
import ru.clevertec.loggingproxy.myspring.annotation.Proxy;
import ru.clevertec.loggingproxy.myspring.config.Config;
import ru.clevertec.loggingproxy.myspring.exception.InterfaceImplementationException;

import java.util.Set;

@Getter
public class JavaConfig implements Config {

    private final Reflections scanner;

    public JavaConfig(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
    }

    @Override
    public <T> Class<?> getImplClass(Class<T> anInterface) {
        Set<Class<? extends T>> subTypesOf = scanner.getSubTypesOf(anInterface);
        Set<Class<?>> typesAnnotatedWithProxy = scanner.getTypesAnnotatedWith(Proxy.class);
        typesAnnotatedWithProxy.retainAll(subTypesOf);
        if (subTypesOf.isEmpty()) {
            throw new InterfaceImplementationException(anInterface + " has 0 implementation");
        }
        if (subTypesOf.size() > 1) {
            if (typesAnnotatedWithProxy.size() > 1) {
                throw new InterfaceImplementationException(anInterface +
                                                           " has more than one implementations with @Proxy annotation");
            } else if (typesAnnotatedWithProxy.size() == 1) {
                return typesAnnotatedWithProxy.iterator().next();
            }
            throw new InterfaceImplementationException(anInterface + " has more than one implementations");
        }
        return subTypesOf.iterator().next();
    }

}
