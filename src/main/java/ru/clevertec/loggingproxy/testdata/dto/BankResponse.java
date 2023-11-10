package ru.clevertec.loggingproxy.testdata.dto;

import lombok.Builder;

@Builder
public record BankResponse(Long id,
                           String name,
                           String address,
                           String phoneNumber) {
}
