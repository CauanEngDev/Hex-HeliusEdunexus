package com.cauanengdev.heliusedunexus.domain.exceptions;

public class InvalidCapacityException extends DomainException {
    public InvalidCapacityException(int capacity) {
        super("Capacity " + capacity + " is smaller than enrolled students or invalid!");
    }
}
