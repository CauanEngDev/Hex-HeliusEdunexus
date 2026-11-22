package com.cauanengdev.heliusedunexus.domain.exceptions;

import com.cauanengdev.heliusedunexus.domain.model.*;

public class SwitchStudentForClassroomException extends DomainException {
    public SwitchStudentForClassroomException(PersonId studentId, ClassroomId classroomId) {
        super("Error occurred while switching student for classroom!\n" +
                "Student: " + studentId + "\n" +
                "Classroom: " + classroomId);
    }
}
