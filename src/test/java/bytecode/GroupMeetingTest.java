package bytecode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupMeetingTest {

    @Test
    void isFull() {
        GroupMeeting groupMeeting = new GroupMeeting();
        groupMeeting.maxNumberOfAttendees = 100;
        groupMeeting.numberOfEnrollment = 10;
        Assertions.assertFalse(groupMeeting.isEnrollmentFull());
    }
}
