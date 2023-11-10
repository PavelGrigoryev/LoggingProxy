package ru.clevertec.loggingproxy;

import ru.clevertec.loggingproxy.myspring.context.ApplicationContext;
import ru.clevertec.loggingproxy.myspring.util.Application;
import ru.clevertec.loggingproxy.testdata.controller.BankController;
import ru.clevertec.loggingproxy.testdata.dto.BankRequest;

public class LoggingProxyApplication {

    public static void main(String[] args) {
        ApplicationContext context = Application.run(LoggingProxyApplication.class);

        BankController bankController = context.getObject(BankController.class);
        bankController.findById(1L);
        bankController.save(BankRequest.builder()
                .name("Darkness")
                .address("str. Wells, 99")
                .phoneNumber("+7 (495) 999-99-99")
                .build());
        bankController.findAll();
        bankController.update(4L, BankRequest.builder()
                .name("Lightness")
                .address("str. Helmuth, 111")
                .phoneNumber("+7 (495) 123-44-56")
                .build());
        bankController.delete(4L);
    }

}
