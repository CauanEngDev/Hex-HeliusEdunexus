package com.cauanengdev.heliusedunexus.domain.exceptions;

import com.cauanengdev.heliusedunexus.domain.model.Classroom;
import com.cauanengdev.heliusedunexus.domain.model.Subject;
import com.cauanengdev.heliusedunexus.domain.model.Teacher;

public class SwitchTeacherForClassException extends RuntimeException {
    public SwitchTeacherForClassException(Teacher teacher, Classroom classroom, Subject subject) {
        super("Cannot switch teacher " + teacher.getFullName() + " for class " + classroom.getFullClassroomName() +
                " or teacher teach " + subject.name() + " in class " + classroom.getFullClassroomName());
    }
}
