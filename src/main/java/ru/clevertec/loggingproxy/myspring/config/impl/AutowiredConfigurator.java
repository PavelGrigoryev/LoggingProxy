package ru.clevertec.loggingproxy.myspring.config.impl;

import lombok.SneakyThrows;
import ru.clevertec.loggingproxy.myspring.annotation.Autowired;
import ru.clevertec.loggingproxy.myspring.config.ObjectConfigurator;
import ru.clevertec.loggingproxy.myspring.context.ApplicationContext;

import java.lang.reflect.Field;

public class AutowiredConfigurator implements ObjectConfigurator {

    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                field.set(t, object);
            }
        }
    }

}
