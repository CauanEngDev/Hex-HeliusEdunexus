package com.cauanengdev.heliusedunexus.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.MonthDay;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class SubjectReportCardTest {
    SubjectReportCard reportCard;

    @BeforeEach
    public void setUp() {
        reportCard = new SubjectReportCard(3, Subject.MATH, new ArrayList<>());
    }

    private Exam createExam(int index, String grade) {
        return new Exam(index, new BigDecimal(grade), MonthDay.of(3, 15));
    }

    // addExam

    @Test
    public void should_addExam_when_belowLimit() {
        Exam exam = createExam(1, "7.5");

        reportCard.addExam(exam);

        assertThat(reportCard.getExams()).containsExactly(exam);
    }

    @Test
    public void should_notAddExam_when_indexAlreadyExists() {
        Exam first = createExam(1, "7.5");
        reportCard.addExam(first);

        reportCard.addExam(createExam(1, "9.0"));

        assertThat(reportCard.getExams()).containsExactly(first);
    }

    @Test
    public void should_notAddExam_when_limitIsReached() {
        reportCard.addExam(createExam(1, "7.0"));
        reportCard.addExam(createExam(2, "8.0"));
        reportCard.addExam(createExam(3, "9.0"));

        reportCard.addExam(createExam(4, "10.0"));

        assertThat(reportCard.getExams()).hasSize(3);
    }

    // removeExam

    @Test
    public void should_removeExam_when_indexExists() {
        Exam first = createExam(1, "7.0");
        Exam second = createExam(2, "8.0");
        reportCard.addExam(first);
        reportCard.addExam(second);

        reportCard.removeExam(1);

        assertThat(reportCard.getExams()).containsExactly(second);
    }

    @Test
    public void should_keepExams_when_removeIndexDoesNotExist() {
        Exam first = createExam(1, "7.0");
        reportCard.addExam(first);

        reportCard.removeExam(99);

        assertThat(reportCard.getExams()).containsExactly(first);
    }

    // editExam

    @Test
    public void should_replaceExam_when_indexExists() {
        reportCard.addExam(createExam(1, "7.0"));
        reportCard.addExam(createExam(2, "8.0"));
        Exam edited = createExam(2, "9.5");

        reportCard.editExam(edited, 2);

        assertThat(reportCard.getExams()).hasSize(2).contains(edited);
        assertThat(reportCard.getExams()).doesNotContain(createExam(2, "8.0"));
    }

    @Test
    public void should_keepExams_when_editIndexDoesNotExist() {
        Exam first = createExam(1, "7.0");
        reportCard.addExam(first);

        reportCard.editExam(createExam(99, "9.5"), 99);

        assertThat(reportCard.getExams()).containsExactly(first);
    }

    // getAverageGrade

    @Test
    public void should_returnSameGrade_when_singleExam() {
        reportCard.addExam(createExam(1, "7.5"));

        assertThat(reportCard.getAverageGrade()).isEqualByComparingTo("7.5");
    }

    @Test
    public void should_averageWithTwoDecimals_when_gradesHaveOneDecimal() {
        reportCard.addExam(createExam(1, "7.5"));
        reportCard.addExam(createExam(2, "8.0"));

        assertThat(reportCard.getAverageGrade()).isEqualByComparingTo("7.8");
    }

    @Test
    public void should_averageWithTwoDecimals_when_gradesAreIntegers() {
        reportCard.addExam(createExam(1, "10"));
        reportCard.addExam(createExam(2, "5"));

        assertThat(reportCard.getAverageGrade()).isEqualByComparingTo("7.5");
    }
}