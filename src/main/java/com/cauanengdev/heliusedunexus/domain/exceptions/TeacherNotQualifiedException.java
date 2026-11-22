package com.cauanengdev.heliusedunexus.domain.exceptions;

import com.cauanengdev.heliusedunexus.domain.model.PersonId;
import com.cauanengdev.heliusedunexus.domain.model.Subject;
import com.cauanengdev.heliusedunexus.domain.model.Teacher;

public class TeacherNotQualifiedException extends DomainException {
    public TeacherNotQualifiedException(PersonId teacherId, Subject subject) {
        super("Teacher is not qualified to teach this subject!" +
                "\nTeacher Id: " + teacherId +
                "\nSubject: " + subject.name());
    }
}
