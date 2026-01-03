
L'intercettazione in Java EE 7 avviene attraverso gli #Interceptor , i quali sono usati per intromettersi sulle invocazioni dei metodi di business, spesso per controllo di problemi tecnici o per preoccupazioni aziendali.
Gli #Interceptor vengono automaticamente attivati dal contenitore quando viene chiamato un metodo di un Bean gestito.

Gli #interceptor sono un modo efficace di disaccoppiare i problemi tecnici dalla logica aziendale.

Quando un client richiama un metodo di un bean gestito, il contenitore è in grado di intercettare la chiamata e elaborare la logica di business prima che il metodo del bean venga invocato.
Gli #Interceptor rientrano in 4 tipi:
- **Constructor-lever interceptors**: intercettore associato ad un costruttore della classe target (@AroundConstruct);
- **Method-level interceptors**: intercettore associato ad uno specifico metodo di business (@AroundInvoke);
- **Timeout method interceptors**: intercettore che si interpone sui metodi di timeout con @AroundTimeout (utilizzato solo con il servizio timer EJB);
- **Life-cycle callback interceptors**: intercettore che si interpone sulle callback dell'evento del ciclo di vita dell'istanza di destinazione (@PostConstruct e @PreDestroy).


Esistono diversi modi per definire l'intercettazione. Il più semplice consiste nell'aggiungere interceptor (a livello di metodo, tiumeout o intercettatori del ciclo di vita) al bean stesso.
La base di un metodo AroundInvoke è:
```Java
@AroundInvoke
Object<METHOD>(InvocationContext ic) throws Exception;
```



La maggior parte delle volte si desidera isolare i problemi trasversali in una classe separata e dire al contenitore di intercettare le chiamate su diversi bean.
Per specificare un #Interceptor di classe, è necessario sviluppare una classe separata ed indicare al contenitore di applicarlo su un metodo specifico di bean o sul bean in generale.

```Java
public class LoggingInterceptor {
	@Inject
	private Logger logger;
	
	@AroundConstruct
	private void init(InvocationContext ic) throws Exception {
		logger.fine("Entering construction");
		try {
			ic.proceed();
		} finally {
			logger.fine("Exiting construction");
		}
	}
	
	@AroundInvoke
	public Object logMethod(InvocationContext ic) throws Exception {
		logger.entering(ic.getTarget().toString(), ic.getMethod().getName());
		try {
			return ic.proceed();
		} finally {
			logger.exiting(ic.getTarget().toString(), ic.getMethod().getName())
		}
	}
}
```

Nel caso sottostante, l'interceptor vale soltanto per createCustomer:
```Java
@Transactional
public class CustomerService {
	@Inject
	private EntityManager em;
	
	@Interceptors(LoggingInterceptor.class)
	public void createCustomer(Customer customer) {
		em.persist(customer);
	}
	
	public Customer findCustomerById(Long id){
		return em.find(Customer.class, id);
	}
}
```

Per farlo valere per tutta la classe, basta scrivere:
```Java
@Transactional
@Interceptors(LoggingIntercepttor.class)
public class CustomerService {
	public void createCustomer(Customer customer) {...}
	public Customer findCustomerById(Long id) {...}
}
```

Se si vuole escludere qualche metodo, basta scrivere:
```Java
@Transactional
@Interceptors(LoggingIntercepttor.class)
public class CustomerService {
	public void createCustomer(Customer customer) {...}
	public Customer findCustomerById(Long id) {...}
	
	@ExcludeClassInterceptors
	public Customer updateCustomer(Customer customer) {...}
}
```



Con un'annotazione di callback, è possibile informare il contenitore per richiamare un metodo in una determinata fase del ciclo di vita (@PostCostruct e @PreDestroy).
Cosa succede se hai bisogno di catturare eventi del ciclo di vita attraverso molti tipi di bean?
Gli intercettori del ciclo di vita consentono di isolare un codice in una classe e di richiamarlo quando viene attivato un evento del ciclo di vita.

L'inserimento di più interception avviene nel seguente modo:
```Java
@Stateless
@Interceptors({I1.class, I2.class})
public class CustomerService {
	public void createCustomer(Customer customer) {...}
	@Interceptors({I3.class, I4.class})
	public Customer findCustomerById(Long id) {...}
	public void removeCustomer(Customer customer) {...}
}
```



Nonostante gli intercettori siano definiti nelle loro specifiche e possano essere utilizzati in qualsiasi bean gestito, la specifica #CDI li ha estesi aggiungendo il #binding di interceptor, utilizzabile solo se CDI è abilitato.
Un #binding di #interceptor è un'annotazione definita dall'utente che è a sua volta annotata @InterceptorBinding che associa la classe interceptor al bean senza alcuna dipendenza diretta tra le due classi.
Una volta creato un binding di interceptor, c'è bisogno di legarlo all'interceptor stesso, annotando l'interceptor sia con @Interceptor che con il binding (in questo caso @Loggable).

```Java
@InterceptorBinding
@Target({METHOD, TYPE})
@Retention(RUNTIME)
public @interface Loggable {}


@Interceptor
@Loggale
```

La specifica di un #interceptor è disabilitata di default, quindi deve essere abilitata nel *bean.xml*.

E' possibile stabilire le priorità di un interceptor utilizzando l'annotazione @Priority. Questo serve per dare un ordine di esecuzione agli interceptor.

**@Priority** prende un numero intero che può assumere qualsiasi valore, ma la regola è che gli intercettori con valori di priorità più piccoli vengono chiamati per primi.
Java EE 7 definisce le priorità a livello di piattaforma e quindi è possibile chiamare gli interceptor prima o dopo determinati [[Eventi]].

L'annotazione interceptor definisce il seguente insieme di costanti:
- PLATFORM_BEFORE = 0: inizio dell'intervallo per i primi intercettori definito dalla piattaforma Java EE;
- LIBRARY_BEFORE = 1000: inizio dell'intervallo per i primi intercettori definito dalle librerie di estensione;
- APPLICAZIONE = 2000: inizio dell'intervallo per intercettori definiti dalle applicazioni;
- LIBRARY_AFTER = 3000: inizio dell'intervallo per gli intercettori ritardati definiti dalle librerie di estensione;
- PLATFORM_AFTER = 4000: inizio dell' intervallo per gli intercettori ritardati definito dalla piattaforma Java EE.