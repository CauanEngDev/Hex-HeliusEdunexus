package com.cauanengdev.heliusedunexus.domain.exceptions;

import com.cauanengdev.heliusedunexus.domain.model.ClassroomId;
import com.cauanengdev.heliusedunexus.domain.model.PersonId;

public class ClassroomFullException extends DomainException {
    public ClassroomFullException(ClassroomId classroomId, PersonId studentId) {
        super("Classroom is full. Cannot enroll student!\n" +
                "Classroom Id: " + classroomId + "\n" +
                "Student Id: " + studentId);
    }
}
