package com.cauanengdev.heliusedunexus.domain.exceptions;

import com.cauanengdev.heliusedunexus.domain.model.Classroom;
import com.cauanengdev.heliusedunexus.domain.model.Student;

public class ClassroomFullException extends RuntimeException {
    public ClassroomFullException(Classroom classroom, Student student) {
        super("Classroom " + classroom.getFullClassroomName() + " is full. Cannot enroll student " + student.getFullName());
    }
}
