@Singleton
@Startup
public class Singleton {
    @Inject
    private EJB ejb;

    private Auto a1 = new Auto(/*inserimento dati relativi alla prima auto dell'esempio*/);
    //effettuare lo stesso procedimento per tutte le restanti auto presenti nel database di esempio

    @PostConstruct
    public void popola(){
        em.add(a1);
        //effettuare lo stesso procedimento per tutte le restanti auto presenti nel database di esempio
    }

    @PreDestroy
    public void svuota(){
        em.clear();
    }
}
