package com.cauanengdev.heliusedunexus.domain.exceptions;

import com.cauanengdev.heliusedunexus.domain.model.SchoolStage;

public class InvalidGradeException extends RuntimeException {
    public InvalidGradeException(int grade, SchoolStage stage) {
        super("Grade " + grade + " is not valid for school stage " + stage.name());
    }
}
