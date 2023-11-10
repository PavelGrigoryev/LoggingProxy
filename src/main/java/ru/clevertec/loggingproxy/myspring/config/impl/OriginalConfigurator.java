package ru.clevertec.loggingproxy.myspring.config.impl;

import lombok.SneakyThrows;
import ru.clevertec.loggingproxy.myspring.annotation.Original;
import ru.clevertec.loggingproxy.myspring.config.ObjectConfigurator;
import ru.clevertec.loggingproxy.myspring.context.ApplicationContext;

import java.lang.reflect.Field;
import java.util.Objects;

public class OriginalConfigurator implements ObjectConfigurator {

    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            Original annotation = field.getAnnotation(Original.class);
            if (Objects.nonNull(annotation)) {
                field.setAccessible(true);
                Object object = context.getObject(annotation.originalImpl());
                field.set(t, object);
            }
        }
    }

}
