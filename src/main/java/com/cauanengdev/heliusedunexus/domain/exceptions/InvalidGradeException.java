package com.cauanengdev.heliusedunexus.domain.exceptions;

import com.cauanengdev.heliusedunexus.domain.model.SchoolStage;

public class InvalidGradeException extends DomainException {
    public InvalidGradeException(int grade, SchoolStage stage) {
        super("Grade " + grade + " is not valid for school stage " + String.valueOf(stage.name()) + "!");
    }
}
