import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.WebLink;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

import java.util.Set;

public class DemoSmallApplication {

    private static String coapServerUri = "coap://coap.me:5683";

    public static void main(String... args) throws Throwable {
        discover();
        get("/hello");
        post();
        put();
        get("/validate");
        delete("/validate");
        get("/validate");
    }

    private static void delete(String path){
        CoapClient client = new CoapClient(coapServerUri + path);
        CoapResponse response = client.delete();
        System.out.println(response.getResponseText());
    }

    private static void put() {
        CoapClient client = new CoapClient(coapServerUri + "/validate");
        CoapResponse response = client.put("Put Payload", MediaTypeRegistry.APPLICATION_LINK_FORMAT);
        System.out.println(response.getResponseText());
    }

    private static void post() {
        CoapClient client = new CoapClient(coapServerUri + "/sink");
        CoapResponse response = client.post("Post Payload", MediaTypeRegistry.APPLICATION_LINK_FORMAT);
        System.out.println(response.getResponseText());
    }

    private static void get(String path) {
        CoapClient client = new CoapClient(coapServerUri + path);
        CoapResponse response = client.get();
        System.out.println(response.getResponseText());
    }

    private static void discover() {
        CoapClient client = new CoapClient(coapServerUri);
        Set<WebLink> response = client.discover();

        for (WebLink link : response) {
            System.out.println(link.getURI());
        }
    }
}
