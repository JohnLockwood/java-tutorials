package RestfulWebService;

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
        Integer id = taskMap.size() + 1;

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
        return taskMap.get(taskId);
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
