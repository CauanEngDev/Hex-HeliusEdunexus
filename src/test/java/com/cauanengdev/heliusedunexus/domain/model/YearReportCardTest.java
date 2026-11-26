package com.cauanengdev.heliusedunexus.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class YearReportCardTest {
    private Classroom classroom;
    private YearReportCard yearReportCard;
    private SubjectReportCard mathReportCard;
    private SubjectReportCard physicsReportCard;

    @BeforeEach
    public void setUp() {
        classroom = new Classroom("A", Year.of(2026), 1, 30, SchoolStage.HIGH_SCHOOL);
        yearReportCard = new YearReportCard(classroom);

        mathReportCard = new SubjectReportCard(3, Subject.MATH);
        physicsReportCard = new SubjectReportCard(3, Subject.PHYSICS);
    }

    @Test
    public void should_initializeWithClassroomDataAndEmptyReports_when_created() {
        assertThat(yearReportCard.getClassroom()).isEqualTo(classroom);
        assertThat(yearReportCard.getYear()).isEqualTo(Year.of(2026));
        assertThat(yearReportCard.getStage()).isEqualTo(SchoolStage.HIGH_SCHOOL);
        assertThat(yearReportCard.getGrade()).isEqualTo(1);
        assertThat(yearReportCard.getAllSubjectReportCards()).isEmpty();
        assertThat(yearReportCard.getSubjectReportCard(Subject.MATH)).isNull();
    }

    @Test
    public void should_reflectUpdatedClassroom_when_setClassroomIsCalled() {
        Classroom newClassroom = new Classroom("B", Year.of(2027), 2, 25, SchoolStage.HIGH_SCHOOL);

        yearReportCard.setClassroom(newClassroom);

        assertThat(yearReportCard.getClassroom()).isEqualTo(newClassroom);
        assertThat(yearReportCard.getYear()).isEqualTo(Year.of(2027));
        assertThat(yearReportCard.getGrade()).isEqualTo(2);
    }

    @Test
    public void should_addAndRetrieveSubjectReportCard_when_addSubjectReportCardIsCalled() {
        yearReportCard.addSubjectReportCard(Subject.MATH, mathReportCard);
        yearReportCard.addSubjectReportCard(Subject.PHYSICS, physicsReportCard);

        assertThat(yearReportCard.getSubjectReportCard(Subject.MATH)).isEqualTo(mathReportCard);
        assertThat(yearReportCard.getAllSubjectReportCards())
                .hasSize(2)
                .containsExactlyInAnyOrder(mathReportCard, physicsReportCard);
    }

    @Test
    public void should_replaceSubjectReportCard_when_editSubjectReportCardIsCalled() {
        SubjectReportCard updatedMathReportCard = new SubjectReportCard(4, Subject.MATH);
        yearReportCard.addSubjectReportCard(Subject.MATH, mathReportCard);

        yearReportCard.editSubjectReportCard(Subject.MATH, updatedMathReportCard);

        assertThat(yearReportCard.getSubjectReportCard(Subject.MATH)).isEqualTo(updatedMathReportCard);
        assertThat(yearReportCard.getAllSubjectReportCards()).containsExactly(updatedMathReportCard);
    }

    @Test
    public void should_removeSubjectReportCard_when_removeSubjectReportCardIsCalled() {
        yearReportCard.addSubjectReportCard(Subject.MATH, mathReportCard);
        yearReportCard.addSubjectReportCard(Subject.PHYSICS, physicsReportCard);

        yearReportCard.removeSubjectReportCard(Subject.MATH);

        assertThat(yearReportCard.getSubjectReportCard(Subject.MATH)).isNull();
        assertThat(yearReportCard.getAllSubjectReportCards()).containsExactly(physicsReportCard);
    }

    @Test
    public void should_keepReports_when_removingSubjectNotPresent() {
        yearReportCard.addSubjectReportCard(Subject.MATH, mathReportCard);

        yearReportCard.removeSubjectReportCard(Subject.HISTORY);

        assertThat(yearReportCard.getAllSubjectReportCards()).containsExactly(mathReportCard);
    }

    @Test
    public void should_notChangeReports_when_returnedListIsModified() {
        yearReportCard.addSubjectReportCard(Subject.MATH, mathReportCard);

        yearReportCard.getAllSubjectReportCards().add(physicsReportCard);

        assertThat(yearReportCard.getAllSubjectReportCards()).containsExactly(mathReportCard);
    }
}