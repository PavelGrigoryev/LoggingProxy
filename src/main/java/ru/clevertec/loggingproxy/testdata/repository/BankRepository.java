package ru.clevertec.loggingproxy.testdata.repository;

import ru.clevertec.loggingproxy.testdata.model.Bank;

import java.util.List;
import java.util.Optional;

public interface BankRepository {

    Optional<Bank> findById(Long id);

    List<Bank> findAll();

    Optional<Bank> save(Bank bank);

    Optional<Bank> update(Bank bank);

    Optional<Bank> delete(Long id);

}
