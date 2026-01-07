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

    public int getId() {
        return id;
    }


    public String getNomeAuto() {
        return nomeAuto;
    }

    public void setNomeAuto(String nomeAuto) {
        this.nomeAuto = nomeAuto;
    }

    public int getChilometraggio() {
        return chilometraggio;
    }

    public void setChilometraggio(int chilometraggio) {
        this.chilometraggio = chilometraggio;
    }

    public Date getAnnoImmatricolazione() {
        return annoImmatricolazione;
    }



    public void setAnnoImmatricolazione(Date annoImmatricolazione) {
        this.annoImmatricolazione = annoImmatricolazione;
    }

    public String getStatoAuto() {
        return statoAuto;
    }

    public void setStatoAuto(String statoAuto) {
        this.statoAuto = statoAuto;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "nomeAuto='" + nomeAuto + '\'' +
                ", chilometraggio=" + chilometraggio +
                ", annoImmatricolazione=" + annoImmatricolazione +
                ", statoAuto='" + statoAuto + '\'' +
                '}';
    }

    @PostPersist
    @PostUpdate
    @PostLoad
    public void setEtaAuto(){
        // logica di business relativa al calcolo dell'età dell'auto alla data odierna (se coincide, etaAuto = 0)
    }
}
