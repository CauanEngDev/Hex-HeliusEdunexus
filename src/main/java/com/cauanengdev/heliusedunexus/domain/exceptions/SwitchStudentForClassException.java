package com.cauanengdev.heliusedunexus.domain.exceptions;

import com.cauanengdev.heliusedunexus.domain.model.*;

public class SwitchStudentForClassException extends DomainException {
    public SwitchStudentForClassException(PersonId studentId, ClassroomId oldClassroomId, int oldClassroomCapacity,
                                          ClassroomId newClassroomId, int newClassroomCapacity) {
        super("Error occurred while switching student for classroom!\n" +
                "Student: " + studentId + "\n" +
                "Old Classroom: " + oldClassroomId + "\n" +
                "Old Classroom Capacity: " + oldClassroomCapacity + "\n" +
                "New Classroom: " + newClassroomId + "\n" +
                "New Classroom Capacity: " + newClassroomCapacity
                );
    }
}
