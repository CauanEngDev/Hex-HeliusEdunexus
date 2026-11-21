package com.cauanengdev.heliusedunexus.domain.exceptions;

public class InvalidExpectedExamsException extends RuntimeException {
    public InvalidExpectedExamsException() {
        super("Number of expected exams must be greater than 0!");
    }
}
