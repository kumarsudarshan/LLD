package meetingscheduler.service;

import meetingscheduler.model.MeetingDetails;

import java.util.List;

public class EmailNotificationService implements Notification {

    public EmailNotificationService() {
    }

    public void sendEmail(String email, MeetingDetails meeting) {
        // send mail
    }

    public void sendMultipleEmail(List<String> emailList, MeetingDetails meeting) {
        for(String email : emailList) {
            sendEmail(email, meeting);
        }
    }
}
