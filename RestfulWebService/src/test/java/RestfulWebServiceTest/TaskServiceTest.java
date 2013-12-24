package RestfulWebServiceTest;

import RestfulWebService.Task;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class TaskServiceTest {

    public static Client client = null;

    @BeforeClass
    public static void Before() {
        TaskServiceTest.client = ClientBuilder.newClient();
    }


    @AfterClass
    public static void After() {
        client.close();
    }

    @Test
    public void canPOSTaNewTask() throws Exception {

        WebTarget target = client.target("http://localhost:8080/webapi").path("tasks");
        Task task = new Task();
        task.setAssigned("john@particlewave.com");
        task.setDescription("Fix post test");

        // Map manually (later we'll use the task directly)
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
    public void canPUTanUpdatedTask() throws Exception {

        String original = "Update me -- don't leave me in this awful, just-created state!";
        String updated = "Freshly updated and open for business.  Thank you so much!";

        // Create the original object
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/webapi").path("tasks");
        Task task = new Task();
        task.setAssigned("bogus@example.com");
        task.setDescription(original);
        Response rs = target.request().post(Entity.entity(task, MediaType.APPLICATION_JSON));
        Task taskResponse = rs.readEntity(Task.class);
        assertNotNull(taskResponse);

        // Now update it, and PUT the updated version
        taskResponse.setDescription(updated);
        target = client.target("http://localhost:8080/webapi").path("tasks/" + taskResponse.getId());
        rs = target.request().put(Entity.entity(taskResponse, MediaType.APPLICATION_JSON));
        Task taskResponseUpdated = rs.readEntity(Task.class);

        // And test...
        assertNotNull(taskResponseUpdated);
        assertEquals(taskResponseUpdated.getDescription(), updated);
    }

    @Test
    public void canGETaTaskList() throws Exception {

        WebTarget target = client.target("http://localhost:8080/webapi");
        WebTarget resource = target.path("/tasks");
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

        WebTarget target = client.target("http://localhost:8080/webapi");
        WebTarget resource = target.path("/tasks/1");
        Invocation.Builder invocationBuilder =
                resource.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        Task task = response.readEntity(Task.class);
        assertNotNull(task);
        assertNotNull(task.getId());
        assertTrue(task.getId() >= 1);
    }

    @Test
    public void canDELETEaTask() throws Exception {

        // Create a task to have one we can safely delete
        WebTarget target = client.target("http://localhost:8080/webapi").path("tasks");
        Task task = new Task();
        task.setAssigned("example@test.com");
        task.setDescription("I can't go on like this.  Please delete me.");
        Response rs = target.request().post(Entity.entity(task, MediaType.APPLICATION_JSON));
        Task taskResponse = rs.readEntity(Task.class);
        assertNotNull(taskResponse);
        assertNotNull(taskResponse.getId());
        assertTrue(taskResponse.getId() >= 1);

        // Set up a URL based on our returned object
        target = client.target("http://localhost:8080/webapi").path("tasks/" + taskResponse.getId());

        // Test that it's saved
        taskResponse = null;
        rs = target.request().get();
        taskResponse = rs.readEntity(Task.class);
        assertNotNull(taskResponse);

        // Goodbye!
        target.request().delete();

        // Test that it's really gone.
        taskResponse = null;
        rs = target.request().get();
        taskResponse = rs.readEntity(Task.class);
        assertNull(taskResponse);
    }


}
