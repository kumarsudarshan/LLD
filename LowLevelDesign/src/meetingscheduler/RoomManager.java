package meetingscheduler;

import java.util.LinkedList;
import java.util.List;

class RoomManager {

    List<Room> meetingRoomList;
    private static RoomManager instance;

    public static RoomManager getInstance() {
        if (instance == null) {
            synchronized (RoomManager.class) {
                if (instance == null) {
                    instance = new RoomManager();
                }
            }
        }
        return instance;
    }

    private RoomManager() {
        this.meetingRoomList = new LinkedList<>();
    }

    public void addMeetingRoom(Room room) {
        meetingRoomList.add(room);
    }

    public void allocateRoom(Room room, MeetingDetails meetingDetails) {
        room.status = RoomStatus.OCCUPIED;
        room.currentMeetingInfo = meetingDetails;
    }

    public void freeTheRoom(Room room, MeetingDetails meetingDetails) {
        room.status = RoomStatus.FREE;
        room.currentMeetingInfo = null;
    }
}
