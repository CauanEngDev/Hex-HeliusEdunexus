package com.cauanengdev.heliusedunexus.domain.exceptions;

import com.cauanengdev.heliusedunexus.domain.model.PersonId;
import com.cauanengdev.heliusedunexus.domain.model.ClassroomId;
import com.cauanengdev.heliusedunexus.domain.model.Subject;

public class AssignTeacherForClassException extends DomainException {
    public AssignTeacherForClassException(PersonId teacherId, ClassroomId classroomId, Subject subject) {
        super("Cannot assign teacher for class or teacher teach in class!\n" +
                "Teacher Id: " + teacherId + "\n" +
                "Classroom Id: " + classroomId + "\n" +
                "Subject: " + subject.name());
    }
}
