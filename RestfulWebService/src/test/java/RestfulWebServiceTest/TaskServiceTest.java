package RestfulWebServiceTest;


import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class TaskServiceTest {

    @Test
    public void testGet() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/webapi");
        WebTarget resource = target.path("/task/1");
        Invocation.Builder invocationBuilder =
                resource.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        //Client client = Client.create();

        /*WebTarget target =
        target
        jc.cre
        */

    }

}
