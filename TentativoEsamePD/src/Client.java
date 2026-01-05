import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Date;
import java.util.List;

public class Client {
    public static void main(String[] args) throws NamingException {
        Context ctx = new InitialContext();

        EJBRemote ejb = (EJBRemote) ctx.lookup("java:global/auto/EJB!package.EJBRemote");

        List<Auto> inVendita = ejb.findInVendita();

        for (Auto auto : inVendita){
            System.out.println(auto);
        }

        Auto auto = new Auto("Ferrari 360", 69000, new Date(2002,11, 01 ), "in vendita");
        ejb.add(auto);
    }
}
