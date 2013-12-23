package RestfulWebService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import org.codehaus.jackson.map.ObjectMapper;

import java.net.URI;
import java.util.List;

/**
 * Root resource (exposed at "task" path)
 */
@Path("/task")
public class TaskRestfulService {


    static TaskModel model = new TaskModel();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> getTasks() {
        List<Task> tasks = model.getAll();
        return tasks;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Task getTask(@PathParam("id") Integer id) {
        Task task = model.get(id);
        return task;
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doPost(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Task task = null;
        try {
            task = mapper.readValue(json, Task.class);
        }
        catch (Exception e) {
            return Response.status(501).build();
        }
        return saveNewDTO(task);

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doPut(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Task task;
        try {
        task = (Task) mapper.readValue(json, Task.class);
        }
        catch(Exception e) {
            return Response.status(501).build();
        }

        if (task.getId() == null || task.getId() == 0) {
            return saveNewDTO(task);
        }

        return saveExistingDTO(task);
    }

    // Used by POST and PUT when entity does not yet exist
    private Response saveNewDTO(Task task) {
        Integer id = model.insert(task);
        URI createdURI = URI.create("http://localhost:8080/webapi/task/" + id);
        task.setId(id);
        return Response
                .status(201)
                .contentLocation(createdURI)
                .entity(task)
                .build();
    }

    // Used by PUT to update an existing entity
    private Response saveExistingDTO(Task task) {
        model.update(task);
        return Response
                .status(200)
                .entity(task)
                .build();
    }
}
