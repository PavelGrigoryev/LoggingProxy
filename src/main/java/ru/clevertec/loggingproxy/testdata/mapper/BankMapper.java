package ru.clevertec.loggingproxy.testdata.mapper;

import ru.clevertec.loggingproxy.myspring.annotation.Singleton;
import ru.clevertec.loggingproxy.testdata.dto.BankRequest;
import ru.clevertec.loggingproxy.testdata.dto.BankResponse;
import ru.clevertec.loggingproxy.testdata.model.Bank;

@Singleton
public class BankMapper {

    public BankResponse toResponse(Bank bank) {
        return BankResponse.builder()
                .id(bank.getId())
                .name(bank.getName())
                .address(bank.getAddress())
                .phoneNumber(bank.getPhoneNumber())
                .build();
    }

    public Bank fromRequest(BankRequest request) {
        return Bank.builder()
                .name(request.name())
                .address(request.address())
                .phoneNumber(request.phoneNumber())
                .build();
    }

}
