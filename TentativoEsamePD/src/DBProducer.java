public class DBProducer {
    @Produces
    @PersistentContext(unitname = "EsamePU")
    private EntityManager em;
}
