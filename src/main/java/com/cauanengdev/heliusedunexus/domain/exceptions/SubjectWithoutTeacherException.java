package com.cauanengdev.heliusedunexus.domain.exceptions;

import com.cauanengdev.heliusedunexus.domain.model.ClassroomId;
import com.cauanengdev.heliusedunexus.domain.model.Subject;

public class SubjectWithoutTeacherException extends DomainException {
    public SubjectWithoutTeacherException(ClassroomId classroomId, Subject subject) {
        super("Subject " + subject.name() + " does not have a teacher assigned for classroom!\n" +
                "Classroom Id: " + classroomId);
    }
}
