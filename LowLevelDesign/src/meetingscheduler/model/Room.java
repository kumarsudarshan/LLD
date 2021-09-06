package meetingscheduler.model;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private int id;
    private String name;
    private RoomStatus status;
    private List<MeetingDetails> meetings;

    public Room(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.status = RoomStatus.FREE;
        meetings = new ArrayList<>();
    }

    public boolean isAvailable(long startTime, long endTime) {
        for (MeetingDetails meeting : meetings) {
            if (startTime >= meeting.getStartTime() && startTime <= (meeting.getStartTime() + meeting.getDuration()) ||
                    endTime >= meeting.getStartTime() && endTime <= (meeting.getStartTime() + meeting.getDuration()) ) {
                return false;
            }
        }
        return true;
    }

    public MeetingDetails bookMeeting(int id, long startTime, long duration, List<String> emailList) {
        MeetingDetails meeting = new MeetingDetails(id, startTime, duration, emailList);
        meetings.add(meeting);
        return meeting;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public List<MeetingDetails> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<MeetingDetails> meetings) {
        this.meetings = meetings;
    }
}
