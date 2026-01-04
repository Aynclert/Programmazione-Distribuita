import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
@NamedQuery(name = "findInVendita", query = "SELECT a FROM Auto a WHERE a.statoAuto = 'in vendita'")
public class Auto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ID @GeneratedValue
    private int id;

    private String nomeAuto;
    private int chilometraggio;
    private Date annoImmatricolazione;
    private String statoAuto;

    @Transient
    private int etaAuto;

    public Auto(){};

    public Auto(String nomeAuto, int chilometraggio, Date annoImmatricolazione, String statoAuto) {
        this.nomeAuto = nomeAuto;
        this.chilometraggio = chilometraggio;
        this.annoImmatricolazione = annoImmatricolazione;
        this.statoAuto = statoAuto;
    }


    //setter, getter e toString

    @PostPersist
    @PostUpdate
    @PostLoad
    public void setEtaAuto(){
        // logica di business relativa al calcolo dell'età dell'auto alla data odierna (se coincide, etaAuto = 0)
    }
}
