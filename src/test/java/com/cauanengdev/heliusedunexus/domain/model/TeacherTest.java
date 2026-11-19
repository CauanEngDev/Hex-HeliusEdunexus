package com.cauanengdev.heliusedunexus.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class TeacherTest {
    private Teacher teacher;
    private Address address;
    private Student student1;
    private Student student2;

    @BeforeEach
    public void setUp() {
        address = new Address("12345000", "012", "Main st", "Belforoxo", "Cityville", "CthuluState");
        teacher = new Teacher(UUID.randomUUID(), "Sherlock", "Holmes", "1111111111",
                LocalDate.of(1854, 1, 6), "sherlock.holmes@teste.com", address, AcademicDegree.MASTER);

        student1 = new Student(UUID.randomUUID(), "Enola", "Holmes", "2222222222",
                LocalDate.of(2008, 3, 15), "enola@teste.com", address, teacher);

        student2 = new Student(UUID.randomUUID(), "John", "Watson", "3333333333",
                LocalDate.of(2009, 7, 20), "john@teste.com", address, teacher);
    }

    @Test
    public void should_teacherAttributes_when_created() {
        assertThat(teacher.getFullName()).isEqualTo("Sherlock Holmes");
        assertThat(teacher.getDegreeLevel()).isEqualTo(AcademicDegree.MASTER);
        assertThat(teacher.getQualifications()).isEmpty();
        assertThat(teacher.getWards()).isEmpty();
    }

    @Test
    public void should_changeDegreeLevel_when_setDegreeLevel() {
        teacher.setDegreeLevel(AcademicDegree.DOCTORATE);

        assertThat(teacher.getDegreeLevel()).isEqualTo(AcademicDegree.DOCTORATE);
    }

    @Test
    public void should_addQualification_when_addQualificationIsCalled() {
        teacher.addQualification(Subject.MATH);
        teacher.addQualification(Subject.PHYSICS);

        assertThat(teacher.getQualifications()).containsExactly(Subject.MATH, Subject.PHYSICS);
    }

    @Test
    public void should_removeQualification_when_qualificationExists() {
        teacher.addQualification(Subject.MATH);
        teacher.addQualification(Subject.PHYSICS);

        teacher.removeQualification(Subject.MATH);

        assertThat(teacher.getQualifications()).containsExactly(Subject.PHYSICS);
    }

    @Test
    public void should_keepQualifications_when_removingQualificationNotPresent() {
        teacher.addQualification(Subject.MATH);

        teacher.removeQualification(Subject.HISTORY);

        assertThat(teacher.getQualifications()).containsExactly(Subject.MATH);
    }

    @Test
    public void should_notChangeQualifications_when_returnedListIsModified() {
        teacher.addQualification(Subject.MATH);

        teacher.getQualifications().add(Subject.HISTORY);

        assertThat(teacher.getQualifications()).containsExactly(Subject.MATH);
    }

    // --- Testes do contrato Responsible ---

    @Test
    public void should_addWard_when_addWardIsCalled() {
        teacher.addWard(student1);
        teacher.addWard(student2);

        assertThat(teacher.getWards()).containsExactlyInAnyOrder(student1, student2);
    }

    @Test
    public void should_notDuplicateWard_when_sameStudentIsAddedTwice() {
        teacher.addWard(student1);
        teacher.addWard(student1);

        assertThat(teacher.getWards()).hasSize(1).containsExactly(student1);
    }

    @Test
    public void should_removeWard_when_wardExists() {
        teacher.addWard(student1);
        teacher.addWard(student2);

        teacher.removeWard(student1.getPersonId());

        assertThat(teacher.getWards()).containsExactly(student2);
    }

    @Test
    public void should_keepWards_when_removingNonExistentPersonId() {
        teacher.addWard(student1);

        teacher.removeWard(new PersonId(UUID.randomUUID()));

        assertThat(teacher.getWards()).containsExactly(student1);
    }

    @Test
    public void should_notChangeWards_when_returnedListIsModified() {
        teacher.addWard(student1);

        teacher.getWards().add(student2);

        assertThat(teacher.getWards()).containsExactly(student1);
    }

    @Test
    public void should_transferWardToNewResponsible_when_switchWardIsCalled() {
        Guardian newResponsible = new Guardian(UUID.randomUUID(), "Mycroft", "Holmes", "4444444444",
                LocalDate.of(1847, 2, 10), "mycroft@teste.com", address);

        teacher.addWard(student1);
        teacher.addWard(student2);

        teacher.switchWard(student1, newResponsible);

        assertThat(teacher.getWards()).containsExactly(student2);
        assertThat(newResponsible.getWards()).containsExactly(student1);
    }
}