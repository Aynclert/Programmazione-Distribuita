import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.print.attribute.standard.Destination;
import java.util.Scanner;

public class Producer {
    public static void main(String[] args) throws NamingException{
        try {
            Context ctx = new InitialContext();
            ConnectionFactory cf = (ConnectionFactory) ctx.lookup("jms/javaee7/ConnectionFactory");
            Destination Topic = ctx.lookup("jms/javaee7/Topic");

            Scanner sc = new Scanner(System.in);
            System.out.print("Inserisci l' ID dell'auto: ");
            int id = sc.nextInt();
            System.out.print("Inserisci il nuovo stato dell'auto: ");
            String stato = sc.nextLine();

            MessageWrapper messageWrapper = new MessageWrapper(id, stato);

            try (JMSContext c = cf.createContext()) {
                c.createProducer().setProperty("stato", stato).send(messageWrapper);
            }
        }
        catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
