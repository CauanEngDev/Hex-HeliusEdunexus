package com.cauanengdev.heliusedunexus.domain.model;

import com.cauanengdev.heliusedunexus.domain.exceptions.InvalidExpectedExamsException;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Getter(AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SubjectReportCard {
    private int expectedExams;
    private Subject subject;

    @Setter(AccessLevel.NONE)
    private List<Exam> exams;

    public SubjectReportCard(int expectedExams, Subject subject) {
        if (expectedExams <= 0) throw new InvalidExpectedExamsException();

        this.subject = subject;
        this.expectedExams = expectedExams;
        this.exams = new ArrayList<>();
    }

    public void setExpectedExams(int expectedExams) {
        if (expectedExams <= 0) throw new InvalidExpectedExamsException();
        this.expectedExams = expectedExams;
    }

    public BigDecimal getAverageGrade() {
        BigDecimal totalGrade = BigDecimal.ZERO;

        for (Exam exam : exams) {
            totalGrade = totalGrade.add(exam.grade());
        }

        return totalGrade.divide(BigDecimal.valueOf(exams.size()), 1,  RoundingMode.HALF_UP);
    }

    public void addExam(Exam exam) {
        boolean exists = exams.stream().anyMatch(e -> e.index() == exam.index());

        if (!exists && exams.size() < expectedExams) exams.add(exam);
    }

    public void removeExam(int examIndex) {
        exams.removeIf(exam -> exam.index() == examIndex);
    }

    public void editExam(Exam newExam, int examIndex) {
        for  (int i = 0; i < exams.size(); i++) {
            if  (exams.get(i).index() == examIndex) {
                exams.set(i, newExam);
                return;
            }
        }
    }
}
