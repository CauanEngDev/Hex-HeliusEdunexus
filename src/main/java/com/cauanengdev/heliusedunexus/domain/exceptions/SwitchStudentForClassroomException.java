package com.cauanengdev.heliusedunexus.domain.exceptions;

import com.cauanengdev.heliusedunexus.domain.model.Classroom;
import com.cauanengdev.heliusedunexus.domain.model.Student;

public class SwitchStudentForClassroomException extends RuntimeException {
    public SwitchStudentForClassroomException(Student student, Classroom classroom) {
        super("Error occurred while switching student " + student.getFullName() + " for classroom " + classroom.getFullClassroomName());
    }
}
