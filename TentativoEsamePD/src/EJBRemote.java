import java.util.List;

public interface EJBRemote {
    public void add(Auto a);
    public void remove(Auto a);
    public Auto update(Auto a);
    public List<Auto> findInVendita();
    public Auto findByID(int id);
}
