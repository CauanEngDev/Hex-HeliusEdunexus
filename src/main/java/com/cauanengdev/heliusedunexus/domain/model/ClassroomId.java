package com.cauanengdev.heliusedunexus.domain.model;

import java.util.UUID;

public record ClassroomId(UUID id) {
    public ClassroomId() { this(UUID.randomUUID()); }
}
