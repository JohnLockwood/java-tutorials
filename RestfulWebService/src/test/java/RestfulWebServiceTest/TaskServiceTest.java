package RestfulWebServiceTest;


import RestfulWebService.TaskDTO;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import javax.ws.rs.client.*;
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

    }

    @Test
    public void testPost() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/webapi").path("task");
        TaskDTO tdto = new TaskDTO();
        tdto.setAssigned("john@particlewave.com");
        tdto.setDescription("Fix post test");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(tdto);
        Response rs = target.request().post(Entity.entity(json, MediaType.APPLICATION_JSON));
        TaskDTO tdtoResponse = rs.readEntity(TaskDTO.class);
        assertNotNull(tdtoResponse);
        assertEquals(tdtoResponse.getAssigned(), tdto.getAssigned());

    }


}
