package com.cauanengdev.heliusedunexus.domain.exceptions;

import com.cauanengdev.heliusedunexus.domain.model.PersonId;

import java.time.Year;

public class StudentAlreadyEnrolledException extends DomainException {
    public StudentAlreadyEnrolledException(PersonId studentId, Year year) {
        super("Student is already enrolled in year!\n" +
                "Year: " + year + "\n" +
                "Student ID: " + studentId);
    }
}
