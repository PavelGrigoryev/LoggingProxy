package ru.clevertec.loggingproxy.testdata.proxy;

import ru.clevertec.loggingproxy.myspring.annotation.Autowired;
import ru.clevertec.loggingproxy.myspring.annotation.Original;
import ru.clevertec.loggingproxy.myspring.annotation.PostConstruct;
import ru.clevertec.loggingproxy.myspring.annotation.Proxy;
import ru.clevertec.loggingproxy.myspring.annotation.Singleton;
import ru.clevertec.loggingproxy.testdata.model.Bank;
import ru.clevertec.loggingproxy.testdata.proxy.log.Loggable;
import ru.clevertec.loggingproxy.testdata.repository.BankRepository;
import ru.clevertec.loggingproxy.testdata.repository.impl.BankRepositoryImpl;

import java.util.List;
import java.util.Optional;

@Proxy
@Singleton
@SuppressWarnings("unchecked")
public class BankRepositoryProxy implements BankRepository {

    @Original(originalImpl = BankRepositoryImpl.class)
    private BankRepository bankRepository;
    @Autowired
    private Loggable loggable;
    private String simpleName;

    @PostConstruct
    public void init() {
        simpleName = bankRepository.getClass().getSimpleName();
    }

    @Override
    public Optional<Bank> findById(Long id) {
        return (Optional<Bank>) loggable.invokeAndLog(bankRepository, simpleName, "findById", id);
    }

    @Override
    public List<Bank> findAll() {
        return (List<Bank>) loggable.invokeAndLog(bankRepository, simpleName, "findAll");
    }

    @Override
    public Optional<Bank> save(Bank bank) {
        return (Optional<Bank>) loggable.invokeAndLog(bankRepository, simpleName, "save", bank);
    }

    @Override
    public Optional<Bank> update(Bank bank) {
        return (Optional<Bank>) loggable.invokeAndLog(bankRepository, simpleName, "update", bank);
    }

    @Override
    public Optional<Bank> delete(Long id) {
        return (Optional<Bank>) loggable.invokeAndLog(bankRepository, simpleName, "delete", id);
    }

}
