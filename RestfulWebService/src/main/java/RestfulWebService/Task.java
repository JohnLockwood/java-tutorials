package RestfulWebService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import org.codehaus.jackson.map.ObjectMapper;

import java.net.URI;

/**
 * Root resource (exposed at "task" path)
 */
@Path("/task")
public class Task {

    public Task () {}

    static TaskModel model = new TaskModel();

    @GET
    //@Produces(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public TaskDTO getTask(@PathParam("id") Integer id) {
        TaskDTO td = model.get(id);
        return td;
    }

    /*
    Don't try to go down this road, using JAXBElements to JSON.
    There be dragons.
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String doPut(JAXBElement<TaskDTO> taskDto) {
        return "inside doPut";
    }
    */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doPost(String json) {
        ObjectMapper mapper = new ObjectMapper();
        TaskDTO taskDto = null;
        try {
            taskDto = mapper.readValue(json, TaskDTO.class);
        }
        catch (Exception e) {
            return Response.noContent().build();
        }
        String id = model.insert(taskDto);
        URI createdURI = URI.create("http://localhost:8080/webapi/task/" + id);
        taskDto.setId(id);
        return Response.status(201).contentLocation(createdURI).entity(taskDto).build();
        //return Response.created(createdURI).build();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String doPut(String json) {
        ObjectMapper mapper = new ObjectMapper();
        TaskDTO dto;
        try {
        dto = (TaskDTO) mapper.readValue(json, TaskDTO.class);
        }
        catch(Exception e) {
            return e.getMessage();
        }

        return "inside doPost, received: " + dto.toString();
    }
}
