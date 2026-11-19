package com.cauanengdev.heliusedunexus.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.Year;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ClassroomTest {
    Classroom classroom;
    Address address;

    @BeforeEach
    public void setUp() {
        address = new Address("12345000", "012", "Main st", "Belforoxo", "Cityville", "CthuluState");
        classroom = new Classroom("A", Year.of(2026), 5, 30, SchoolStage.ELEMENTARY);
    }

    private Teacher createTeacher(String firstName) {
        return new Teacher(UUID.randomUUID(), firstName, "Teste", "1111111111",
                LocalDate.of(1980, 1, 1), firstName.toLowerCase() + "@teste.com", address, AcademicDegree.BACHELOR);
    }

    // creation

    @Test
    public void should_classroomAttributes_when_created() {
        assertThat(classroom.getClassroomId()).isNotNull();
        assertThat(classroom.getSection()).isEqualTo("A");
        assertThat(classroom.getYear()).isEqualTo(Year.of(2026));
        assertThat(classroom.getGrade()).isEqualTo(5);
        assertThat(classroom.getStage()).isEqualTo(SchoolStage.ELEMENTARY);
        assertThat(classroom.getCapacity()).isEqualTo(30);
        assertThat(classroom.getStudentIds()).isEmpty();
        assertThat(classroom.getTeachersAndSubjects()).isEmpty();
    }

    @Test
    public void should_changeCapacity_when_setCapacity() {
        classroom.setCapacity(40);

        assertThat(classroom.getCapacity()).isEqualTo(40);
    }

    // changeGrade

    @ParameterizedTest
    @CsvSource({
            "KINDERGARTEN, 1", "KINDERGARTEN, 3",
            "ELEMENTARY, 1", "ELEMENTARY, 5",
            "MIDDLE_SCHOOL, 6", "MIDDLE_SCHOOL, 9",
            "HIGH_SCHOOL, 1", "HIGH_SCHOOL, 3"
    })
    public void should_changeStageAndGrade_when_combinationIsValid(SchoolStage stage, int grade) {
        classroom.changeGrade(stage, grade);

        assertThat(classroom.getStage()).isEqualTo(stage);
        assertThat(classroom.getGrade()).isEqualTo(grade);
    }

    @ParameterizedTest
    @CsvSource({
            "KINDERGARTEN, 0", "KINDERGARTEN, 4",
            "ELEMENTARY, 0", "ELEMENTARY, 6",
            "MIDDLE_SCHOOL, 5", "MIDDLE_SCHOOL, 10",
            "HIGH_SCHOOL, 0", "HIGH_SCHOOL, 4"
    })
    public void should_keepStageAndGrade_when_combinationIsInvalid(SchoolStage stage, int grade) {
        classroom.changeGrade(stage, grade);

        assertThat(classroom.getStage()).isEqualTo(SchoolStage.ELEMENTARY);
        assertThat(classroom.getGrade()).isEqualTo(5);
    }

    // teachers

    @Test
    public void should_assignTeacher_when_assignTeacherToSubject() {
        Teacher teacher = createTeacher("Sherlock");

        classroom.assignTeacherToSubject(Subject.MATH, teacher);

        assertThat(classroom.getTeacherBySubject(Subject.MATH)).isEqualTo(teacher);
    }

    @Test
    public void should_replaceTeacher_when_switchTeacherOnAssignedSubject() {
        Teacher oldTeacher = createTeacher("Sherlock");
        Teacher newTeacher = createTeacher("Hermione");
        classroom.assignTeacherToSubject(Subject.MATH, oldTeacher);

        classroom.assignTeacherToSubject(Subject.MATH, newTeacher);

        assertThat(classroom.getTeacherBySubject(Subject.MATH)).isEqualTo(newTeacher);
    }

    @Test
    public void should_assignTeacher_when_switchTeacherOnSubjectWithoutTeacher() {
        Teacher teacher = createTeacher("Tony");

        classroom.assignTeacherToSubject(Subject.PHYSICS, teacher);

        assertThat(classroom.getTeacherBySubject(Subject.PHYSICS)).isEqualTo(teacher);
    }

    @Test
    public void should_notChangeTeachers_when_returnedMapIsModified() {
        classroom.getTeachersAndSubjects().put(Subject.MATH, createTeacher("Elizabeth"));

        assertThat(classroom.getTeachersAndSubjects()).isEmpty();
    }

    // students

    @Test
    public void should_notChangeStudentIds_when_returnedSetIsModified() {
        classroom.getStudentIds().add(new PersonId(UUID.randomUUID()));

        assertThat(classroom.getStudentIds()).isEmpty();
    }

    // equals / hasId

    @Test
    public void should_trueInEquals_when_comparedWithItself() {
        assertThat(classroom.equals(classroom)).isTrue();
    }

    @Test
    public void should_falseInEquals_when_classroomsHaveDifferentIds() {
        Classroom other = new Classroom("A", Year.of(2026), 5, 30, SchoolStage.ELEMENTARY);

        assertThat(classroom.equals(other)).isFalse();
    }

    @Test
    public void should_falseInEquals_when_comparedWithNullOrOtherType() {
        assertThat(classroom.equals(null)).isFalse();
        assertThat(classroom.equals("A")).isFalse();
    }

    @Test
    public void should_hasId_when_idMatches() {
        assertThat(classroom.hasId(classroom.getClassroomId())).isTrue();
        assertThat(classroom.hasId(new ClassroomId(UUID.randomUUID()))).isFalse();
    }
}