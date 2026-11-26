package com.cauanengdev.heliusedunexus.domain.model;

import com.cauanengdev.heliusedunexus.domain.exceptions.SwitchStudentForResponsibleException;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

public class Teacher extends Person implements Responsible {
    private Map<PersonId, Student> wards;
    private EnumSet<Subject> qualifications;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private AcademicDegree degreeLevel;

    public Teacher(String firstName, String lastName, String phone, LocalDate birthday,
                   String email, Address address, AcademicDegree degreeLevel) {
        super(firstName, Optional.ofNullable(lastName), phone, birthday, email, address);
        this.degreeLevel = degreeLevel;
        this.wards = new HashMap<>();
        this.qualifications = EnumSet.noneOf(Subject.class);
    }

    public Teacher(UUID id, String firstName, String lastName, String phone, LocalDate birthday,
                   String email, Address address, AcademicDegree degreeLevel) {
        super(id, firstName, Optional.ofNullable(lastName), phone, birthday, email, address);
        this.degreeLevel = degreeLevel;
        this.wards = new HashMap<>();
        this.qualifications = EnumSet.noneOf(Subject.class);
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

    public List<Subject> getQualifications() {
        return new ArrayList<>(qualifications);
    }

    public void addQualification(Subject qualification) {
        qualifications.add(qualification);
    }

    public void removeQualification(Subject qualification) {
        qualifications.remove(qualification);
    }
}
