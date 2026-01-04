@WebService
@Stateless
@LocalBean
public class EJB implements EJBRemote{
    @Inject
    private EntityManager em;

    public EJB(){}

    public void add(Auto a){
        em.persist(a);
    }

    public void remove(Auto a){
        em.remove(a);
    }

    public Auto update(Auto a){
        return em.merge(a);
    }

    public List<Auto> findInVendita(){
        return em.createNamedQuery("findInVendita").getResultList;
    }

    public Auto findByID(int id){
        String query = "SELECT a FROM Auto a WHERE a.id =" + id;
        return em.createDynamicQuery(query).getSingleResult;
    }
}
