package taskplanner.model;

public class Task {
    private int id;
    private String subject;
    private User user;
    private TaskType taskType;
    private TaskStatus taskStatus;

    public Task(int id, String subject, User user, TaskType taskType) {
        this.id = id;
        this.subject = subject;
        this.user = user;
        this.taskType = taskType;
        this.taskStatus = TaskStatus.OPEN;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
