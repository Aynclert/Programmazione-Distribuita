import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.print.attribute.standard.Destination;

public class Consumer implements MessageListener{
    public static void main(String[] args){
        try {
            Context ctx = new InitialContext();
            ConnectionFactory cf = (ConnectionFactory) ctx.lookup("jms/javaee7/ConnectionFactory");
            Destination topic = (Destination) ctx.lookup("jms/javaee7/Topic");
            try (JMSContext context = cf.createContext()){
                context.createConsumer(topic, "chilometraggio > 50000").setMessageListener(new Listener());
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void onMessage(Message message){
        try{
            MessageWrapper messageWrapper = message.getBody(MessageWrapper.class);
            Auto auto = ejb.findByID();
            System.out.println(auto,getID);
        } catch (JMSException e){
            e.printStackTrace();
        }
    }
}
