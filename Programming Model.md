La maggior parte delle specifiche Java EE 7 utilizza lo stesso modello di programmazione #POJO con alcuni metadati (annotazioni o XML) distribuiti in un contenitore. La maggior parte delle volte il POJO non implementa nemmeno un'interfaccia o estende una superclasse. Grazie ai metadati, il contenitore sa quali servizi applicare a questo componente distribuito. In Java EE 7, servlet, bean di backing JSF, EJB, entità, servizi Web #SOAP e REST sono classi annotate con descrittori di distribuzione #XML  facoltativi. 

```Java
// bean di supporto JSF che è una classe Java con una sola annotazione CDI.
@Named
public class BookController {

	@Inject
	private BookEJB bookEJB;
	
	private Book boot = new Book();
	private List<Book> bookList = new ArrayList<Book>();
	
	public String doCreateBook() {
		book = bookEJB.createBook(book);
		bookList = bookEJB.findBooks();
		return "listBook.xhtml";
	}
	// ...
}
```

Anche gli #EJB seguono lo stesso modello.  Se è necessario accedere a un EJB localmente, è sufficiente una semplice classe annotata senza interfaccia. Gli EJB possono anche essere distribuiti direttamente in un file war senza essere precedentemente impacchettati in un file jar. Ciò rende EJB il componente transazionale più semplice che può essere utilizzato da semplici applicazioni Web a quelle aziendali complesse.

```Java
@Stateless
public class BookEJB {
	@Inject
	private EntityManager em;
	public Book findBookById(Long id) {
		return em.find(Book.class, id);
	}
	
	public Book createBook(Book book) {
		em.persist(book);
		return book;
	}
}
```

I servizi web restful si sono fatti strada nelle applicazioni moderne. Java EE 7 risponde alle esigenze delle imprese migliorando le specifiche JAX-RS. 
Un servizio web RESTful è una classe Java annotata che risponde alle azioni HTTP:

```Java
@Path("books")
public class BookResource {
	@Inject private EntityManager em;

	@GET
	@Produces({"application/xml", "application/json"})
	public List<Book> getAllBooks() {
		Query query = em.createNamedQuery("findAllBooks");
		List<Book> books = query.getResultList();
		return books;
	}
}
```

