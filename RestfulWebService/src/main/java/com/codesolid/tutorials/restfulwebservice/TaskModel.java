package com.codesolid.tutorials.restfulwebservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Our "data model" is simply an in-memory map of IDs to
 * TaskDTOs (Data Transfer Objects).
 *
 * The world's dumbest key-value pair, NoSQL database.
 *
 */
public class TaskModel {
    HashMap<Integer, Task> taskMap = new HashMap<Integer, Task>();

    public TaskModel() {
        // Make sure we can always see at least one object by setting up a test
        // object
        createTestObject();
    }

    private synchronized void createTestObject(){
        Task td = new Task();
        td.setAssigned("unluckyintern@example.com");
        td.setDescription("Get coffee for developers");
        td.setId(1);
        insert(td);
    }

    // Store a new task.
    public synchronized Integer insert(Task task) {

        // If we have an explicit ID, use it.
        // E.g. PUT with a specific ID creating a new resource.
        Integer id = task.getId();

        // However, usually for insert what we want
        // for insert is more like an AUTO_INCREMENT or IDENTITY
        // field in SQL.
        if (id == null) {
            id = taskMap.size() + 1;
        }

        task.setId(id);
        taskMap.put(id, task);
        return id;
    }

    // Delete a task
    public synchronized void delete(Integer taskId) {
        if (taskMap.containsKey(taskId)) {
            taskMap.remove(taskId);
        }
    }

    // Update an existing task
    public synchronized void update(Task task) {
        taskMap.put(task.getId(), task);
    }

    // Get a single task
    public synchronized Task get(int taskId) {
        return isTaskSaved(taskId) ? taskMap.get(taskId) : null;
    }

    // Do we have such a task?
    public synchronized boolean isTaskSaved(int taskId) {
        return taskMap.containsKey(taskId);
    }

    // Get a list of all the tasks in the map
    public synchronized List<Task> getAll() {
        List<Task> tasks = new ArrayList<Task>();
        for(Task t : taskMap.values()) {
            tasks.add(t);
        }
        return tasks;
    }
}
