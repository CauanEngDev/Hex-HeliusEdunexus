package com.cauanengdev.heliusedunexus.domain.exceptions;

import com.cauanengdev.heliusedunexus.domain.model.Student;

import java.time.Year;

public class StudentAlreadyEnrolledException extends RuntimeException {
    public StudentAlreadyEnrolledException(Student student, Year year) {
        super("Student " + student.getFullName() + " is already enrolled in year " + year);
    }
}
