package ru.clevertec.loggingproxy.testdata.controller;

import lombok.extern.slf4j.Slf4j;
import ru.clevertec.loggingproxy.myspring.annotation.Autowired;
import ru.clevertec.loggingproxy.myspring.annotation.PostConstruct;
import ru.clevertec.loggingproxy.testdata.dto.BankRequest;
import ru.clevertec.loggingproxy.testdata.dto.BankResponse;
import ru.clevertec.loggingproxy.testdata.dto.DeleteResponse;
import ru.clevertec.loggingproxy.testdata.service.BankService;

import java.util.List;

@Slf4j
public class BankController {

    @Autowired
    private BankService bankService;

    @PostConstruct
    public void init() {
        log.warn("Service: {}", bankService.getClass().getSimpleName());
    }

    public BankResponse findById(Long id) {
        return bankService.findById(id);
    }

    public List<BankResponse> findAll() {
        return bankService.findAll();
    }

    public BankResponse save(BankRequest request) {
        return bankService.save(request);
    }

    public BankResponse update(Long id, BankRequest request) {
        return bankService.update(id, request);
    }

    public DeleteResponse delete(Long id) {
        return bankService.delete(id);
    }

}
