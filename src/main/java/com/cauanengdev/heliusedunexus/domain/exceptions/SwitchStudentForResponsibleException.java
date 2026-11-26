package com.cauanengdev.heliusedunexus.domain.exceptions;

import com.cauanengdev.heliusedunexus.domain.model.PersonId;
import com.cauanengdev.heliusedunexus.domain.model.Responsible;
import com.cauanengdev.heliusedunexus.domain.model.Student;

public class SwitchStudentForResponsibleException extends DomainException {
    public SwitchStudentForResponsibleException(PersonId studentId, PersonId responsibleId) {
        super("Cannot switch student for responsible!\n" +
                "Student Id: " + studentId + "\n" +
                "Responsible Id: " + responsibleId);
    }
}
