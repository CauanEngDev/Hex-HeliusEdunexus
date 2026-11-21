package com.cauanengdev.heliusedunexus.domain.exceptions;

import com.cauanengdev.heliusedunexus.domain.model.Classroom;
import com.cauanengdev.heliusedunexus.domain.model.Subject;

public class SubjectWithoutTeacherException extends RuntimeException {
    public SubjectWithoutTeacherException(Classroom classroom, Subject subject) {
        super("Subject " + subject.name() + " does not have a teacher assigned for classroom " + classroom.getFullClassroomName());
    }
}
