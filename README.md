# LoggingProxy

## Автор: [Grigoryev Pavel](https://pavelgrigoryev.github.io/GrigoryevPavel/)

### Основано на конференции Евгения Борисова [video](https://youtu.be/rd6wxPzXQvo)

### Реализовано:

1. Реализован свой Spring, который находится в [my spring](src/main/java/ru/clevertec/loggingproxy/myspring)
2. Тестируемые объекты находятся в [testdata](src/main/java/ru/clevertec/loggingproxy/testdata)
3. Для запуска приложения нужно просто
   запустить [main](src/main/java/ru/clevertec/loggingproxy/LoggingProxyApplication.java)
4. Мои Proxy находятся в [proxy](src/main/java/ru/clevertec/loggingproxy/testdata/proxy)
5. Чтоб Proxy работал:
    * Нужно создать свой класс и заимплементить интерфейс проксируемого класса
    * Нужна аннотация @Proxy над proxy классом
    * Через аннотацию @Original внедрить имплементируемый класс, где originalImpl это оригинальный класс чьи методы
      буду вызываться
    * Через аннотацию @Autowired внедрить Loggable для логов если нужно
    * Поставить аннотацию @Log над методами оригинального класса, которого собираемся логировать
    * Proxy готов к работе

#### Пример класса с аннотацией @Log над методом, который собираемся проксировать:

````java

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

}
````

#### Пример Proxy:

````java

@Proxy
@Singleton
public class BankServiceProxy implements BankService {

    @Original(originalImpl = BankServiceImpl.class)
    private BankService bankService;
    @Autowired
    private Loggable loggable;

}
````
