package RestfulWebService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Our "data model" is simply an in-memory map of IDs to
 * TaskDTOs (Data Transfer Objects).
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

    public synchronized Integer insert(Task task) {
        Integer id = taskMap.size() + 1;
        task.setId(id);
        taskMap.put(id, task);
        return id;
    }

    public synchronized void delete(Integer taskId) {
        if (taskMap.containsKey(taskId)) {
            taskMap.remove(taskId);
        }
    }

    public synchronized void update(Task task) {
        if (taskMap.containsKey(task.getId())) {
            taskMap.remove(task.getId());
        }
        taskMap.put(task.getId(), task);

    }

    // Let's exception be thrown if not found.
    public synchronized Task get(int taskId) {
        return taskMap.get(taskId);
    }

    // Let's exception be thrown if not found.
    public synchronized List<Task> getAll() {
        List<Task> tasks = new ArrayList<Task>();
        for(Task t : taskMap.values()) {
            tasks.add(t);
        }
        return tasks;
    }
}
