
L'iniezione in Java EE 7 avviene attraverso i Bean gestiti, che fungono da fondamenta comune per i diversi tipi di #componenti esistenti nella piattaforma. 
I Bean Gestiti altro non sono che oggetti gestiti da un container e che supportano un insieme di servizi base:
- risorse di Injection;
- gestione del ciclo di vita;
- Interception.

Gli oggetti #CDI sono costruiti sul modello dei Bean Gestiti. i quali:
- hanno un ciclo di vita migliorato per oggetti @Stateful;
- portano ad un approccio favorevole a [[Injection]], [[Interception]] e [[Decoration]];
- sono specializzati con annotazioni di tipo [[Qualificatori]].


Se si desidera eseguire un bean CDI all'interno di un contenitore, è necessario **iniettare** il bean, ed il contenitore fa il resto.
Quando si inietta un bean, il contenitore è il responsabile della creazione dell'istanza, risolve le dipendenze e richiama qualsiasi metodo annotato con @PostConstruct pima della prima chiamata al metodo business sul bean.
La notifica di callback @PreDestroy segnala che l'istanza è in fase di rimozione dal contenitore.

I bean CDI vivono in un ambito ben definito (request, application, conversation), e possono essere #Stateful e #Contextual.
I riferimenti iniettati ai bean sono anche consapevoli del contesto, ovvero l'intera catena delle dipendenze del bean è #Contextual.
Il contenitore gestisce automaticamente tutti i bean all'interno dell'ambito e, al termine della sessione, li distrugge automaticamente.
Diversi client di un bean #Stateful vedono il bean in stati diversi: quando un bean è #Stateful, i client in esecuzione nello stesso contesto vedranno la stessa istanza del bean. I client in contesti diversi potrebbero vedere un'istanza diversa a seconda della relazione tra i contesti.

CDI utilizza annotazioni fortemente tipizzate (Qualificatori, stereotipi e binding di #Interceptor) per collegare insieme i bean. L'utilizzo dei descrittori #XML è ridotto al minimo.

Il descrittore di implementazione di un Bean CDI si chiama *beans.xml* ed è obbligatorio. Esso può essere usato per configurare determinate funzionalità, ma è necessario per abilitare il CDI, poiché quest'ultimo ha bisogno di identificare i bean nel percorso della classe (bean discovery) per trasformare i #POJO in bean CDI.


Un bean #CDI può essere un qualsiasi tipo di classe contenente la logica aziendale. Esso può essere richiamato direttamente dal codice Java tramite [[Injection]], oppure richiamato tramite EL da una pagina #JSF.

Secondo la specifica #CDI, il contenitore tratta come un bean CDI qualsiasi classe che soddisfi le seguenti condizioni:
- non è una classe interna o statica;
- è una classe concreta, o è annotata #Decorator;
- ha un costruttore predefinito senza parametri, o dichiara un costruttore annotato #Inject.
Un bean può avere un ambito opzionale, un nome EL facoltativo, un insieme di collegamenti di #Interceptor e una gestione facoltativa del ciclo di vita.

In CDI è possibile anche iniettare le primitive, i tipi di array e qualsiasi #POJO non abilitato in CDI grazie ai #Producers, con annotazione @Produces.

Se un archivio non ha un *bean.xml* sotto la directory META-INF, #CDI non attiverà la scoperta dei bean e i relativi #POJO non potranno essere trattati come bean e di conseguenza essere iniettati. L'unico modo per essere in grado di iniettare #POJO è usare i campi o metodi #Producers .

```Java
public class NumberProducer {
	@Produces @ThirteenDigits
	private String prefix13digits = "13-";
	
	@Produces @ThirteenDigits
	private int editorNumber = 84356;
	
	@Produces @Random
	public double random() {
		return Math.abs(new Random().nextInt());
	}
}
```

Alcuni metodi di produzione restituiscono oggetti che richiedono una distruzione esplicita, come una connessione #JDBC, una sessione [[JMS]] o un [[Entity Manager]].
Se, per la creazione, CDI utilizza i #producers, per la distruzione utilizza i #Disposers, con annotazione @Dispose.

Ogni oggetto gestito da #CDI ha uno scopo ben preciso e un ciclo di vita che è legato a un contesto specifico. Con CDI, un bean è associato a un contesto e rimane in tale contesto fino a quando il bean non viene distrutto dal contenitore. Non c'è modo di rimuovere manualmente un bean da un contesto.
CDI ha unito i livelli #Web e di servizio associandoli a scopi significativi. #CDI definisce i seguenti ambiti incorporati e fornisce anche punti di estensione in modo da poter creare il proprio:
- **@ApplicationScoped**: si estende per l'intera durata di un'applicazione. Il bean viene creato una sola volta per tutta la durata dell'applicazione e viene scartato quando l'applicazione viene chiusa. E' utile per le classi di utilità o helper o per gli oggetti che memorizzano i dati condivisi dall'intera applicazione.
- **@SessionScoped**: comprende diverse richieste HTTP o più invocazioni di metodi per la sessione di un singolo utente. Il bean viene creato per la durata di una sessione HTTP e viene scartato al termine della sessione. E' per gli oggetti necessari durante la sessione.
- **@RequestScoped**: corrisponde a una singola richiesta HTTP o a una chiamata di un metodo. Il bean viene creato per la durata dell'invocazione del metodo e viene scartato al termine del metodo. Viene utilizzato per le classi di servizio o i bean di backup #JSF necessari solo per la durata di una richiesta HTTP.
- **@ConversationScoped**: comprende più chiamate all'interno dei limiti della sessione con i punti iniziale e finale determinati dall'applicazione. Le conversazioni vengono utilizzate su più pagine come parte di un flusso di lavoro a più fasi.
- **@Dependent**: il ciclo di vita è lo stesso del client. Un bean dipendente viene creato ogni volta che viene iniettato e il riferimento viene rimosso quando viene rimosso il target di iniezione. Questo è l'ambito predefinito per CDI.


L'ambito della #conversation mantiene lo stato associato ad un utente, include più richieste ed è demarcato a livello di codice dall'applicazione. Un bean @ConversationScoped può essere utilizzato per un processo di lunga durata in cui vi è un inizio e una fine definiti.
A differenza degli oggetti con ambito conversazione hanno un ciclo di vita ben definito che inizia e termina esplicitamente a livello di codice utilizzando l' #API #Conversation.

```Java
@ConversationScoped
public class CustomerCreatorWizard implements Serializable {

	private Login login;
	private Account account;
	
	@Inject
	private CustomerService customerService;
	
	//avviene l'iniezione della conversazione
	@Inject
	private Conversation conversation;
	
	public void saveLogin(){
		//avviene l'avvio della conversazione
		conversation.begin();
		
		login = new Login();
		//Sets login properties
	}
	
	public void saveAccount(){
		account = new Account();
		//Sets account properties
	}
	
	public void createCustomer(){
		Customer customer = new Customer();
		customer.setLogin(login);
		customer.setAccount(account);
		customerService.createCustomer(customer);
		
		//una volta richiamato l'ultimo passo, viene terminata la conversazione
		conversation.end();
	}
}
```