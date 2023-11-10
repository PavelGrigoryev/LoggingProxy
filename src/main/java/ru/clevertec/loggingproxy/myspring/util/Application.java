package ru.clevertec.loggingproxy.myspring.util;

import lombok.experimental.UtilityClass;
import ru.clevertec.loggingproxy.myspring.config.impl.JavaConfig;
import ru.clevertec.loggingproxy.myspring.context.ApplicationContext;
import ru.clevertec.loggingproxy.myspring.factory.ObjectFactory;

@UtilityClass
public class Application {

    public ApplicationContext run(Class<?> classToScan) {
        String packageToScan = classToScan.getPackageName();
        JavaConfig config = new JavaConfig(packageToScan);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setFactory(objectFactory);
        return context;
    }

}
