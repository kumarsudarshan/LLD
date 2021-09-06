package meetingscheduler.repository;

import meetingscheduler.model.MeetingDetails;
import meetingscheduler.model.Room;

import java.util.List;

public interface Storage {

    public void addMeetingRoom(Room room);

    public void addMeeting(Room room, MeetingDetails meetingDetails);

    public MeetingDetails getMeetingDetails(Room meetingRoom);

    public void removeMeeting(Room meetingRoom);

    public List<Room> getAllMeetingRooms();
}
