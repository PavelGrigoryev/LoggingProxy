package ru.clevertec.loggingproxy.myspring.context;

import lombok.Getter;
import lombok.Setter;
import ru.clevertec.loggingproxy.myspring.annotation.Singleton;
import ru.clevertec.loggingproxy.myspring.config.Config;
import ru.clevertec.loggingproxy.myspring.factory.ObjectFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    @Setter
    private ObjectFactory factory;
    @Getter
    private final Config config;
    private final Map<Class<?>, Object> cache;

    public ApplicationContext(Config config) {
        this.config = config;
        cache = new ConcurrentHashMap<>();
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> type) {
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }
        Class<?> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
        T t = (T) factory.createObject(implClass);
        if (implClass.isAnnotationPresent(Singleton.class)) {
            cache.put(type, t);
        }
        return t;
    }

}
