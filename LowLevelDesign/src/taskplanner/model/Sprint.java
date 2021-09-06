package taskplanner.model;

import java.util.ArrayList;
import java.util.List;

public class Sprint {
    private String name;
    private int start;
    private int end;
    List<Task> tasks;

    public Sprint(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
        tasks = new ArrayList<>();

    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean equal(Sprint sprint) {
        return this.start == sprint.getStart() && this.end == sprint.getEnd();
    }
}
