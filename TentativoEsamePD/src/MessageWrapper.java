import java.io.Serializable;

public class MessageWrapper implements Serializable {
    public static final long serialVersionUID = 1L;

    private int id;
    private String nuovoStato;

    public MessageWrapper() {};

    public  MessageWrapper(int id, String nuovoStato) {
        this.id = id;
        this.nuovoStato = nuovoStato;
    }

    //getter, setter and toString
}
