package ru.clevertec.loggingproxy.testdata.proxy;

import ru.clevertec.loggingproxy.myspring.annotation.Autowired;
import ru.clevertec.loggingproxy.myspring.annotation.Original;
import ru.clevertec.loggingproxy.myspring.annotation.PostConstruct;
import ru.clevertec.loggingproxy.myspring.annotation.Proxy;
import ru.clevertec.loggingproxy.myspring.annotation.Singleton;
import ru.clevertec.loggingproxy.testdata.dto.BankRequest;
import ru.clevertec.loggingproxy.testdata.dto.BankResponse;
import ru.clevertec.loggingproxy.testdata.dto.DeleteResponse;
import ru.clevertec.loggingproxy.testdata.proxy.log.Loggable;
import ru.clevertec.loggingproxy.testdata.service.BankService;
import ru.clevertec.loggingproxy.testdata.service.impl.BankServiceImpl;

import java.util.List;

@Proxy
@Singleton
public class BankServiceProxy implements BankService {

    @Original(originalImpl = BankServiceImpl.class)
    private BankService bankService;
    @Autowired
    private Loggable loggable;
    private String simpleName;

    @PostConstruct
    public void init() {
        simpleName = bankService.getClass().getSimpleName();
    }

    @Override
    public BankResponse findById(Long id) {
        return (BankResponse) loggable.invokeAndLog(bankService, simpleName, "findById", id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BankResponse> findAll() {
        return (List<BankResponse>) loggable.invokeAndLog(bankService, simpleName, "findAll");
    }

    @Override
    public BankResponse save(BankRequest request) {
        return (BankResponse) loggable.invokeAndLog(bankService, simpleName, "save", request);
    }

    @Override
    public BankResponse update(Long id, BankRequest request) {
        return (BankResponse) loggable.invokeAndLog(bankService, simpleName, "update", id, request);
    }

    @Override
    public DeleteResponse delete(Long id) {
        return (DeleteResponse) loggable.invokeAndLog(bankService, simpleName, "delete", id);
    }

}
