package com.codesolid.tutorials.restfulwebservice;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 * This is a Jersey resource.  The RESOURCE_URL we define here, "tasks",
 * is added on to our base Jersey url_pattern of "/webapi/" (see web.xml).
 *
 * So the starting point for all URLs for this resource will look something
 * like http://localhost:8080/webapi/tasks on a test server, for example,
 * or http://localhost/webapi/tasks in production.
 */
@Path(TaskRestfulService.RESOURCE_URL)
public class TaskRestfulService {

    public static final String RESOURCE_URL = "tasks";

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

    /* Handle a POST request, i.e., a request to create a new Task object.
    */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doPost(Task task, @Context UriInfo uriInfo) {
        return saveNewTask(task, uriInfo);
    }

    // Used by POST and PUT when entity does not yet exist
    private Response saveNewTask(Task task, UriInfo uriInfo) {
        Integer id = model.insert(task);
        URI createdURI = URI.create(uriInfo.getBaseUri().toString() + RESOURCE_URL + "/" + id);
        task.setId(id);
        return Response
                .status(Status.CREATED)
                .contentLocation(createdURI)
                .entity(task)
                .build();
    }

    /* A more verbose version of the same POST handler.  Don't write your code
       this way, it's provided for illustration purposes so you can see what's going
       on behind the scenes.

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doPost(String json, @Context UriInfo uriInfo) {

        ObjectMapper mapper = new ObjectMapper();
        Task task = null;
        try {
            task = mapper.readValue(json, Task.class);
        }
        catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        return saveNewTask(task, uriInfo);
    }
    */

    /* PUT to overwrites (updates) a pre-existing task.  Optionally,
       it can store a new task as well.
    */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response doPut(@PathParam("id") Integer id, Task task, @Context UriInfo uriInfo) {

        if (model.isTaskSaved(id)) {
            // In case of a disagreement, the pathParam overrides
            // any id that may be set in the POST body
            if (id > 0) {
                task.setId(id);
            }
            return updateExistingTask(task);
        }
        else {
            return saveNewTask(task, uriInfo);
        }
    }

    private Response updateExistingTask(Task task) {
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
