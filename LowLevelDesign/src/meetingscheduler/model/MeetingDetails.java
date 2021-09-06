package meetingscheduler.model;

import java.util.List;

public class MeetingDetails {
    private int id;
    private long startTime;
    private long duration;
    private List<String> emailList;

    public MeetingDetails(Integer id, long startTime, long duration, List<String> emailList) {
        this.id = id;
        this.startTime = startTime;
        this.duration = duration;
        this.emailList = emailList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public List<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<String> emailList) {
        this.emailList = emailList;
    }
}
