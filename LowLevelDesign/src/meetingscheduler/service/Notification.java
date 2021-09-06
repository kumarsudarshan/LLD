package meetingscheduler.service;

import meetingscheduler.model.MeetingDetails;

import java.util.List;

public interface Notification {
    public void sendEmail(String email, MeetingDetails meeting);

    public void sendMultipleEmail(List<String> emailList, MeetingDetails meeting);
}
