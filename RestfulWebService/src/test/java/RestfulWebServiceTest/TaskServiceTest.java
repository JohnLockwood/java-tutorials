package RestfulWebServiceTest;

import RestfulWebService.Task;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TaskServiceTest {

    @Test
    public void canPOSTaNewTask() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/webapi").path("task");
        Task task = new Task();
        task.setAssigned("john@particlewave.com");
        task.setDescription("Fix post test");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(task);
        Response rs = target.request().post(Entity.entity(json, MediaType.APPLICATION_JSON));
        Task taskResponse = rs.readEntity(Task.class);
        assertNotNull(taskResponse);
        assertEquals(taskResponse.getAssigned(), task.getAssigned());
    }


    // In order not to rely on the fact that our toy model
    // creates a test object for us, we create one here and then get update it.
    @Test
    public void canPUTAnUpdatedTask() throws Exception {

        String original = "Update me -- don't leave me in this awful, just-created state!";
        String updated = "Freshly updated and open for business.  Thank you so much!";

        // Create the original object
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/webapi").path("task");
        Task task = new Task();
        task.setAssigned("bogus@example.com");
        task.setDescription(original);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(task);
        Response rs = target.request().post(Entity.entity(json, MediaType.APPLICATION_JSON));
        Task taskResponse = rs.readEntity(Task.class);
        assertNotNull(taskResponse);


        // Now update it
        taskResponse.setDescription(updated);
        json = mapper.writeValueAsString(taskResponse);
        rs = target.request().put(Entity.entity(json, MediaType.APPLICATION_JSON));
        Task taskResponseUpdated = rs.readEntity(Task.class);
        assertNotNull(taskResponseUpdated);

        assertEquals(taskResponseUpdated.getDescription(), updated);
    }

    @Test
    public void canGETaTaskList() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/webapi");
        WebTarget resource = target.path("/task");
        Invocation.Builder invocationBuilder =
                resource.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        String tasks = response.readEntity(String.class);
        assertNotNull(tasks);
        ObjectMapper mapper = new ObjectMapper();
        Task[] taskArray = mapper.readValue(tasks, Task[].class);
        assertNotNull(taskArray);
        assertTrue(taskArray.length >= 1);
    }

    @Test
    public void canGETaSingleTask() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/webapi");
        WebTarget resource = target.path("/task/1");
        Invocation.Builder invocationBuilder =
                resource.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        Task task = response.readEntity(Task.class);
        assertNotNull(task);
        assertNotNull(task.getId());
        assertTrue(task.getId() >= 1);
    }


}
