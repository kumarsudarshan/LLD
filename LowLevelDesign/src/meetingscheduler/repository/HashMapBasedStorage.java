package meetingscheduler.repository;

import meetingscheduler.model.MeetingDetails;
import meetingscheduler.model.Room;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapBasedStorage implements Storage {

    private static HashMapBasedStorage instance;
    Map<Room, MeetingDetails> mapOfMeetingRoomVsMeeting;
    List<Room> meetingRoomList;

    private HashMapBasedStorage() {
        mapOfMeetingRoomVsMeeting = new ConcurrentHashMap<>();
        meetingRoomList = new ArrayList<>();
    }

    public static HashMapBasedStorage getInstance() {
        if (instance == null) {
            synchronized (HashMapBasedStorage.class) {
                if (instance == null) {
                    instance = new HashMapBasedStorage();
                }
            }
        }
        return instance;
    }

    @Override
    public void addMeetingRoom(Room room){
        meetingRoomList.add(room);
    }

    @Override
    public void addMeeting(Room room, MeetingDetails meetingDetails) {
        mapOfMeetingRoomVsMeeting.put(room, meetingDetails);
    }

    @Override
    public MeetingDetails getMeetingDetails(Room meetingRoom) {
        return mapOfMeetingRoomVsMeeting.get(meetingRoom);
    }

    @Override
    public void removeMeeting(Room meetingRoom) {
        mapOfMeetingRoomVsMeeting.remove(meetingRoom);
    }

    @Override
    public List<Room> getAllMeetingRooms() {
        return meetingRoomList;
    }
}
