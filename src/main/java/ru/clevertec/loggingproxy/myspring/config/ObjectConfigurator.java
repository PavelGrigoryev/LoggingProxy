package ru.clevertec.loggingproxy.myspring.config;

import ru.clevertec.loggingproxy.myspring.context.ApplicationContext;

public interface ObjectConfigurator {

    void configure(Object t, ApplicationContext context);

}
