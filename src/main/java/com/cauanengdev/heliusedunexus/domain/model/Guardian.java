package com.cauanengdev.heliusedunexus.domain.model;

import com.cauanengdev.heliusedunexus.domain.exceptions.SwitchStudentForResponsibleException;

import java.time.LocalDate;
import java.util.*;

public class Guardian extends Person implements Responsible {
    private Map<PersonId, Student> wards;

    public Guardian(String firstName, String lastName, String phone,
                    LocalDate birthday, String email, Address address) {
        super(firstName, Optional.ofNullable(lastName), phone, birthday, email, address);
        this.wards = new HashMap<>();
    }

    public Guardian(UUID id, String firstName, String lastName, String phone,
                    LocalDate birthday, String email, Address address) {
        super(id, firstName, Optional.ofNullable(lastName), phone, birthday, email, address);
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
        if (newResponsible.getWards().contains(ward)) {
            throw new SwitchStudentForResponsibleException(ward.getPersonId(), newResponsible.getPersonId());
        }

        this.removeWard(ward.getPersonId());
        newResponsible.addWard(ward);
    }
}
