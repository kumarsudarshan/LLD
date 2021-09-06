package taskplanner.service;

import taskplanner.model.Sprint;
import taskplanner.model.Task;

import java.util.ArrayList;
import java.util.List;

public class SprintService {

    private static SprintService instance = null;
    List<Sprint> sprintList;

    private SprintService() {
        sprintList = new ArrayList<>();
    }

    public static SprintService getInstance() {
        if (instance == null) {
            synchronized (SprintService.class) {
                if (instance == null) {
                    return new SprintService();
                }
            }
        }
        return instance;
    }

    public Sprint createSprint(String name, int start, int end) {
        return new Sprint(name, start, end);
    }

    public void addToSprint(Sprint sprint, Task task) {
        sprint.getTasks().add(task);
    }

    public void removeFromSprint(Sprint sprint, Task task) {
        sprint.getTasks().remove(task);
    }

}
