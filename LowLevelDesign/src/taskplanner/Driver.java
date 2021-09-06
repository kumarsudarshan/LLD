package taskplanner;

import taskplanner.model.Sprint;
import taskplanner.model.Task;
import taskplanner.model.TaskType;
import taskplanner.model.User;
import taskplanner.service.SprintService;
import taskplanner.service.TaskService;

public class Driver {
    public static void main(String[] args) {
        TaskService taskservice = TaskService.getInstance();
        SprintService sprintService = SprintService.getInstance();

        User user1 = new User(1, "user1");
        User user2 = new User(2, "user2");

        Sprint sprint = sprintService.createSprint("Sprint1", 11, 19);
        Task task = taskservice.createTask(1, "release", user1, TaskType.STORY);

        sprintService.addToSprint(sprint, task);

        taskservice.printAllTasks();

    }
}
