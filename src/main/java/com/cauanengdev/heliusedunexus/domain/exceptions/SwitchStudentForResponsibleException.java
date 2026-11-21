package com.cauanengdev.heliusedunexus.domain.exceptions;

import com.cauanengdev.heliusedunexus.domain.model.Responsible;
import com.cauanengdev.heliusedunexus.domain.model.Student;

public class SwitchStudentForResponsibleException extends RuntimeException {
    public SwitchStudentForResponsibleException(Student student, Responsible responsible) {
        super("Cannot switch student " + student.getFullName() + " for responsible " + responsible.getFullName());
    }
}
