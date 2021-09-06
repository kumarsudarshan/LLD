package taskplanner.service;

import taskplanner.model.*;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private static TaskService instance = null;

    List<Task> taskList;

    private TaskService() {
        taskList = new ArrayList<>();
    }

    public static TaskService getInstance() {
        if (instance == null) {
            synchronized (TaskService.class) {
                if (instance == null) {
                    return new TaskService();
                }
            }
        }
        return instance;
    }

    public Task createTask(int id, String subject, User user, TaskType taskType) {
        Task task = new Task(id, subject, user, taskType);
        addTask(task);
        return task;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void printAllTasks() {
        for (Task task : taskList) {
            System.out.println("[ Task Id: " + task.getId() + ", Task subject: " + task.getSubject() + ", Assigned to: " + task.getUser() + ", Task type: " + task.getTaskType() + "Task status: " + task.getTaskStatus() + "]");
        }
    }

    public void changeStatus(Task task, TaskStatus taskStatus) {
        task.setTaskStatus(taskStatus);
    }

    public void removeTask(Task task) {
        taskList.remove(task);
    }
}
