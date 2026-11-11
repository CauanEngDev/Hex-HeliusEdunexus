package com.cauanengdev.heliusedunexus.domain.model;

import java.util.UUID;

public record PersonId(UUID id) {
    public PersonId() {
        this(UUID.randomUUID());
    }
}
