package ru.clevertec.loggingproxy.testdata.dto;

import lombok.Builder;

@Builder
public record BankRequest(String name,
                          String address,
                          String phoneNumber) {
}
