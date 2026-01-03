
Gli #eventi consentono ai bean di interagire senza alcuna dipendenza dal tempo di compilazione.
Un bean può definire un evento, un altro bean può attivare l'evento e un altro ancora lo può gestire.
Questo schema di base segue il modello di osservatore/osservabile della "Gang of Four".
I produttori di eventi attivano gli eventi utilizzando l'interfaccia @Event. Un produttore solleva gli #eventi chiamando il metodo **fire()**, passa l'oggetto evento e non dipende dall'osservatore.

```Java
public class BookService {
	@Inject
	private NumberGenerator numberGenerator;
	
	@Inject
	private Event<Book> bookAddedEvent;
	
	public Book createBook(String title, Float price, String description) {
		Book book = new Book(title, price, description);
		book.setIsbn(numberGenerator.generateNumber());
		bookAddedEvent.fire();
		return book;
	}
}
```

L'evento è così mandato ad un osservatore, il quale può essere un bean con uno o più metodi osservatori. Ogni parametro del metodo dell'osservatore è annotato con @Observes.

```Java
public class InventoryService {
	@Inject
	private Logger logger;
	Listz<Book> inventory = new ArrayList<>();
	
	public void addBook(@Observes Book book) {
		logger.info("Adding book " + book.getTitle() + " to inventory");
		inventory.add(book);
	}
}
```

In presenza di uno o più eventi, si possono aggiungere delle proprie annotazioni.
