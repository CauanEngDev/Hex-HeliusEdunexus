package com.cauanengdev.heliusedunexus.domain.model;

import java.time.LocalDate;
import java.util.*;

public class Guardian extends Person implements Responsible {
    private Map<PersonId, Student> wards;

    public Guardian(String firstName, String lastName, String phone,
                    LocalDate birthday, String email, Address address) {
        super(firstName, Optional.ofNullable(lastName), phone, birthday, email, address);
        this.wards = new HashMap<>();
    }

    @Override
    public List<Student> getWards() {
        return new ArrayList<>(wards.values());
    }

    @Override
    public void addWard(Student ward) {
        wards.put(ward.getPersonId(), ward);
    }

    @Override
    public void removeWard(PersonId wardId) {
        wards.remove(wardId);
    }

    @Override
    public void switchWard(Student ward, Responsible newResponsible) {
        this.removeWard(ward.getPersonId());
        newResponsible.addWard(ward);
    }
}
