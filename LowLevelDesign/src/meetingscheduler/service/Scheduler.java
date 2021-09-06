package meetingscheduler.service;

import meetingscheduler.exception.RoomOccupiedException;
import meetingscheduler.model.MeetingDetails;
import meetingscheduler.model.Room;
import meetingscheduler.model.RoomStatus;
import meetingscheduler.repository.Storage;
import meetingscheduler.repository.StorageFactory;
import meetingscheduler.repository.StorageType;

public class Scheduler {

    private static Scheduler instance;
    Storage storage;
    Notification notification;

    public static Scheduler getInstance(StorageType storageType, Notification notification) {
        if (instance == null) {
            synchronized (Scheduler.class) {
                if (instance == null) {
                    instance = new Scheduler(storageType, notification);
                }
            }
        }
        return instance;
    }

    private Scheduler(StorageType storageType, Notification notification) {
        this.storage = StorageFactory.getStorage(storageType);
        this.notification = notification;
    }

    public void scheduleMeeting(MeetingDetails meetingDetails) throws RoomOccupiedException {
        Room room = getFreeMeetingRoom();
        if (room != null) {
            if (room.getStatus() == RoomStatus.OCCUPIED) {
                throw new RoomOccupiedException("Room occupied");
            }
            if (room.isAvailable(meetingDetails.getStartTime(), meetingDetails.getStartTime() + meetingDetails.getDuration())) {
                allocateRoom(room, meetingDetails);
                storage.addMeeting(room, meetingDetails);
            }
            System.out.println("Meeting Room Allocated for Meeting : " + meetingDetails.getId());
        } else {
            System.out.println("Meeting Room Could Not Be Allocated for Meeting : " + meetingDetails.getId() + " .. Discarding.. ");
        }

    }

    public void checkForTheMeetingCompletion() {
        synchronized (this) {
            for (Room meetingRoom : storage.getAllMeetingRooms()) {
                MeetingDetails meetingDetails = storage.getMeetingDetails(meetingRoom);
                if (meetingDetails != null && System.currentTimeMillis() > (meetingDetails.getStartTime() + meetingDetails.getDuration())) {
                    System.out.println("Meeting Room : " + meetingRoom.getName() + " is free now as the meeting : " + meetingDetails.getId() + " has ended.. ");
                    freeTheRoom(meetingRoom, meetingDetails);
                    storage.removeMeeting(meetingRoom);
                }
            }
        }
    }

    public Room getFreeMeetingRoom() {
        for (Room room : storage.getAllMeetingRooms()) {
            if (room.getStatus() == RoomStatus.FREE) {
                return room;
            }
        }
        return null;
    }

    public void addMeetingRoom(Room room) {
        storage.addMeetingRoom(room);
    }

    public void allocateRoom(Room room, MeetingDetails meetingDetails) {
        room.setStatus(RoomStatus.OCCUPIED);
        room.getMeetings().add(meetingDetails);
    }

    public void freeTheRoom(Room room, MeetingDetails meetingDetails) {
        room.setStatus(RoomStatus.FREE);
        room.getMeetings().remove(meetingDetails);
    }
}
