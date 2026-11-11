package com.cauanengdev.heliusedunexus.domain.model;

import java.util.Optional;

public record Address(String postalCode,
                      String houseNumber,
                      String street,
                      String neighborhood,
                      String city,
                      String state) { }
