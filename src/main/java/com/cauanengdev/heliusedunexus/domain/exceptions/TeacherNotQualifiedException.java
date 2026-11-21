package com.cauanengdev.heliusedunexus.domain.exceptions;

import com.cauanengdev.heliusedunexus.domain.model.Subject;
import com.cauanengdev.heliusedunexus.domain.model.Teacher;

public class TeacherNotQualifiedException extends RuntimeException {
    public TeacherNotQualifiedException(Teacher teacher, Subject subject) {
        super("Teacher " + teacher.getFullName() + " is not qualified to teach subject " + subject.name());
    }
}
