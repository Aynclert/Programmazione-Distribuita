import A.EJB.endpoint.EJBService;

public class WebServiceClient {

    private EJBService service;

    public static void main(String[] args) {

        System.out.println(findByID2(1));

        System.out.println(findByID2(4));

        System.out.println(findByID2(100));

    }

    public static String findByID2(int id) {
        EJBService service = new EJBService();
        A.endpoint.EJB port = service.getEJBPort();
        return port.findByID2(id);
    }
}