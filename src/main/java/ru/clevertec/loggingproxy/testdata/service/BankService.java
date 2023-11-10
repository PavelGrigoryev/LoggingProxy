package ru.clevertec.loggingproxy.testdata.service;

import ru.clevertec.loggingproxy.testdata.dto.BankRequest;
import ru.clevertec.loggingproxy.testdata.dto.BankResponse;
import ru.clevertec.loggingproxy.testdata.dto.DeleteResponse;

import java.util.List;

public interface BankService {

    BankResponse findById(Long id);

    List<BankResponse> findAll();

    BankResponse save(BankRequest request);

    BankResponse update(Long id, BankRequest request);

    DeleteResponse delete(Long id);

}
