import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        Context ctx = new InitialContext();

        EJBRemote ejb = ctx.lookup("java:global/auto/EJB!package.EJBRemote");

        List<Auto> inVendita = ejb.findInVendita();

        for (Auto auto : inVendita){
            System.out.println(auto);
        }

        Auto auto = new Auto("Ferrari 360", 69000, 11 2002, "in vendita");
        ejb.add(auto);
    }
}
