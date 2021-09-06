package meetingscheduler;

import meetingscheduler.exception.RoomDoesNotExistsException;
import meetingscheduler.exception.RoomOccupiedException;
import meetingscheduler.model.MeetingDetails;
import meetingscheduler.model.Room;
import meetingscheduler.repository.StorageType;
import meetingscheduler.service.EmailNotificationService;
import meetingscheduler.service.Scheduler;

import java.util.Arrays;

public class SchedulerDemo {

    public static void main(String[] args) throws RoomDoesNotExistsException, RoomOccupiedException {

        Scheduler scheduler = Scheduler.getInstance(StorageType.IN_MEMORY, new EmailNotificationService());

        scheduler.addMeetingRoom(new Room(1, "Room1"));
        scheduler.addMeetingRoom(new Room(2, "Room2"));
        scheduler.addMeetingRoom(new Room(3, "Room3"));
        scheduler.addMeetingRoom(new Room(4, "Room4"));
        scheduler.addMeetingRoom(new Room(5, "Room5"));

        MeetingDetails meetingDetails1 = new MeetingDetails(1, System.currentTimeMillis() + 1000, 10000, Arrays.asList("kumar@gmail.com"));
        MeetingDetails meetingDetails2 = new MeetingDetails(2, System.currentTimeMillis() + 2000, 10000, Arrays.asList("kumar@gmail.com"));
        MeetingDetails meetingDetails3 = new MeetingDetails(3, System.currentTimeMillis() + 3000, 10000, Arrays.asList("kumar@gmail.com"));
        MeetingDetails meetingDetails4 = new MeetingDetails(4, System.currentTimeMillis() + 4000, 10000, Arrays.asList("kumar@gmail.com"));
        MeetingDetails meetingDetails5 = new MeetingDetails(5, System.currentTimeMillis() + 5000, 10000, Arrays.asList("kumar@gmail.com"));
        MeetingDetails meetingDetails6 = new MeetingDetails(6, System.currentTimeMillis() + 6000, 10000, Arrays.asList("kumar@gmail.com"));
        MeetingDetails meetingDetails7 = new MeetingDetails(7, System.currentTimeMillis() + 7000, 10000, Arrays.asList("kumar@gmail.com"));

        scheduler.scheduleMeeting(meetingDetails1);
        scheduler.scheduleMeeting(meetingDetails2);
        scheduler.scheduleMeeting(meetingDetails3);
        scheduler.scheduleMeeting(meetingDetails4);
        scheduler.scheduleMeeting(meetingDetails5);
        scheduler.scheduleMeeting(meetingDetails6);
        scheduler.scheduleMeeting(meetingDetails7);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    scheduler.checkForTheMeetingCompletion();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t1 = new Thread(runnable);
        t1.start();
    }
}

