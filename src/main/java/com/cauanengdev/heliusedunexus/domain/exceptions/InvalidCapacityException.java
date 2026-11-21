package com.cauanengdev.heliusedunexus.domain.exceptions;

public class InvalidCapacityException extends RuntimeException {
    public InvalidCapacityException(int capacity) {
        super("Capacity " + capacity + " is smalller than 1 or greater than enrolled students!");
    }
}
