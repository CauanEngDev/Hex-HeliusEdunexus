package com.cauanengdev.heliusedunexus.domain.model;

import lombok.*;

import java.util.List;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SubjectReportCard {
    private int expectedExams;
    private Subject subject;

    @Setter(AccessLevel.NONE)
    private List<Exam> exams;

    public double getAverageGrade() {
        double averageGrade = 0;

        for (Exam exam : exams) {
            averageGrade += exam.grade();
        }

        return averageGrade / exams.size();
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
