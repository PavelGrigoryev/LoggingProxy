package ru.clevertec.loggingproxy.testdata.exception;

public class BankNotFoundException extends RuntimeException {

    public BankNotFoundException(String message) {
        super(message);
    }

}
