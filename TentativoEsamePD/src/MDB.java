@MessageDriver(mappedname = "jms/javaee7/Topic")
public class MDB implements MessageListener{
    @Inject
    private EntityManager em;

    public void onMessage(Message message){
        try{
            MessageWrapper messageWrapper = message.getBody(MessageWrapper.class);
            Auto auto = ejb.findByID(messageWrapper.getID);
            String vecchioStato = auto.getStatoAuto();
            auto.setStatoAuto = (messageWrapper.getNuovoStato());
            ejb.update(auto);

            if(vecchioStato.equals("in vendita") && messageWrapper.getNuovoStato().equals("venduta")){
                System.out.println("Nuovo auto venduta!");
            }
        } catch(JMSException e) {
            e.printStackTrace();
        }
    }
}
