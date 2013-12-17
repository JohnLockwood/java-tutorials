package RestfulWebService;

import java.util.HashMap;

/**
 * Our "data model" is simply an in-memory map of IDs to
 * TaskDTOs (Data Transfer Objects).
 */
public class TaskModel {
    HashMap<Integer, TaskDTO> taskMap = new HashMap<Integer, TaskDTO>();

    public TaskModel() {
        // Make sure we can always see at least one object by setting up a test
        // object
        createTestObject();
    }

    private void createTestObject(){
        TaskDTO td = new TaskDTO();
        td.setAssigned("unluckyintern@example.com");
        td.setDescription("Get coffee for developers");
        td.setId("1");
        insert(td);
    }

    public String insert(TaskDTO task) {
        Integer id = taskMap.size() + 1;
        task.setId(id.toString());
        taskMap.put(id, task);
        return id.toString();
    }

    public void delete(Integer taskId) {
        if (taskMap.containsKey(taskId)) {
            taskMap.remove(taskId);
        }
    }

    public void update(TaskDTO task) {
        if (taskMap.containsKey(task.getId())) {
            taskMap.remove(task.getId());
        }
        taskMap.put(Integer.parseInt(task.getId()), task);

    }

    // Let's exception be thrown if not found.
    public TaskDTO get(int taskId) {
        return taskMap.get(taskId);
    }

}
