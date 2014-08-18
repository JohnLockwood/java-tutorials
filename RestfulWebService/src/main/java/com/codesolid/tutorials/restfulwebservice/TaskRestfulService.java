package com.codesolid.tutorials.restfulwebservice;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import java.net.URI;
import java.util.List;

/**
 * Resource (exposed at "/task" path underneath webapi)
 */
@Path("/tasks")
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

    /*
       Handle a POST request, i.e., a request to create a new Task object.
       We're going to Unpack our JSON String manually for illustration purposes.
       A more concise way to do this will be shown in the PUT handler, below
     */
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

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response doPut(@PathParam("id") Integer id, Task task) {
        // Only assume a new resource if the Id isn't specified anywhere
        if (task.getId() == null || task.getId() == 0 && id > 0) {
            return saveNewDTO(task);
        }
        else if (id > 0) {
            // If the ID is specified, make sure our data matches it.
            task.setId(id);
        }

        model.update(task);
        return Response
                .status(200)
                .entity(task)
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteTask(@PathParam("id") Integer id) {
        model.delete(id);
        return Response.accepted().build();
    }
}
