package com.cauanengdev.heliusedunexus.domain.exceptions;

import java.math.BigDecimal;

public class InvalidNoteException extends RuntimeException {
    public InvalidNoteException(BigDecimal note) {
        super("Invalid note: " + note + ". Note must be between 0 and 10.");
    }
}
