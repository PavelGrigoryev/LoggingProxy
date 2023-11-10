package ru.clevertec.loggingproxy.testdata.service.impl;

import ru.clevertec.loggingproxy.myspring.annotation.Autowired;
import ru.clevertec.loggingproxy.myspring.annotation.Log;
import ru.clevertec.loggingproxy.myspring.annotation.Singleton;
import ru.clevertec.loggingproxy.testdata.dto.BankRequest;
import ru.clevertec.loggingproxy.testdata.dto.BankResponse;
import ru.clevertec.loggingproxy.testdata.dto.DeleteResponse;
import ru.clevertec.loggingproxy.testdata.exception.BankNotFoundException;
import ru.clevertec.loggingproxy.testdata.mapper.BankMapper;
import ru.clevertec.loggingproxy.testdata.model.Bank;
import ru.clevertec.loggingproxy.testdata.repository.BankRepository;
import ru.clevertec.loggingproxy.testdata.service.BankService;

import java.util.List;
import java.util.Optional;

@Singleton
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private BankMapper bankMapper;

    @Log
    @Override
    public BankResponse findById(Long id) {
        return bankRepository.findById(id)
                .map(bankMapper::toResponse)
                .orElseThrow(() -> new BankNotFoundException("Bank with ID " + id + " is not found!"));
    }

    @Override
    public List<BankResponse> findAll() {
        return bankRepository.findAll()
                .stream()
                .map(bankMapper::toResponse)
                .toList();
    }

    @Log
    @Override
    public BankResponse save(BankRequest request) {
        return Optional.of(request)
                .map(bankMapper::fromRequest)
                .flatMap(bankRepository::save)
                .map(bankMapper::toResponse)
                .orElseThrow();
    }

    @Override
    public BankResponse update(Long id, BankRequest request) {
        return Optional.of(request)
                .map(req -> {
                    Bank bank = bankMapper.fromRequest(req);
                    bank.setId(id);
                    return bank;
                })
                .flatMap(bankRepository::update)
                .map(bankMapper::toResponse)
                .orElseThrow();
    }

    @Log
    @Override
    public DeleteResponse delete(Long id) {
        return bankRepository.delete(id)
                .map(bank -> new DeleteResponse("Bank with ID " + id + " was successfully deleted"))
                .orElseThrow(() -> new BankNotFoundException("No Bank with ID " + id + " to delete"));
    }

}
