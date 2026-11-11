package com.cauanengdev.heliusedunexus.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Optional;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public abstract class Person {
    @Setter(AccessLevel.NONE)
    protected PersonId personId;

    @Setter(AccessLevel.NONE)
    protected String firstName;

    @Setter(AccessLevel.NONE)
    protected Optional<String> lastName;

    protected String phone;
    protected LocalDate birthday;
    protected String email;
    protected Address address;

    public Person(String firstName, Optional<String> lastName, String phone,
                  LocalDate birthday, String email, Address address) {
        this.personId = new PersonId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.birthday = birthday;
        this.email = email;
        this.address = address;
    }

    public String getFullName() {
        String fullName = firstName;

        if (Optional.ofNullable(lastName).isPresent() && lastName.isPresent()) {
            fullName += " " + lastName.get();
        }

        return fullName;
    }

    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = Optional.ofNullable(lastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person other) {
            return this.personId.equals(other.personId);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() { return this.personId.hashCode(); }

    public boolean hasId(PersonId id) {
        return this.personId.equals(id);
    }
}
