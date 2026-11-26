package com.cauanengdev.heliusedunexus.domain.model;

import com.cauanengdev.heliusedunexus.domain.exceptions.InvalidNoteException;

import java.math.BigDecimal;
import java.time.MonthDay;

public record Exam(int index, BigDecimal grade, MonthDay date) {
    public Exam {
        if (grade.compareTo(BigDecimal.ZERO) < 0 || grade.compareTo(new BigDecimal("10")) > 0) {
            throw new InvalidNoteException(grade);
        }
    }
}