package ru.clevertec.loggingproxy.testdata.repository.impl;

import ru.clevertec.loggingproxy.myspring.annotation.Log;
import ru.clevertec.loggingproxy.myspring.annotation.Singleton;
import ru.clevertec.loggingproxy.testdata.model.Bank;
import ru.clevertec.loggingproxy.testdata.repository.BankRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Singleton
public class BankRepositoryImpl implements BankRepository {

    private final Map<Long, Bank> database = new HashMap<>(Map.of(
            1L, Bank.builder().id(1L).name("ChicagoBank").address("str. Smith, 1").phoneNumber("+7 (495) 555-55-55").build(),
            2L, Bank.builder().id(2L).name("Big-Bank").address("str. of Peace, 10").phoneNumber("+7 (495) 777-77-77").build(),
            3L, Bank.builder().id(3L).name("SallyBank").address("str. Tables, 5").phoneNumber("+7 (495) 888-88-88").build()
    ));

    @Override
    public Optional<Bank> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Log
    @Override
    public List<Bank> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<Bank> save(Bank bank) {
        long id = database.keySet().stream()
                          .max(Long::compare)
                          .orElse(0L) + 1;
        bank.setId(id);
        database.put(id, bank);
        return Optional.of(bank);
    }

    @Log
    @Override
    public Optional<Bank> update(Bank bank) {
        Long id = bank.getId();
        if (database.containsKey(id)) {
            database.put(id, bank);
            return Optional.of(bank);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Bank> delete(Long id) {
        return Optional.ofNullable(database.remove(id));
    }

}
