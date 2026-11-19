package com.cauanengdev.heliusedunexus.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YearReportCard {
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private Classroom classroom;
    private Map<Subject, SubjectReportCard> subjectReports;

    public YearReportCard(Classroom classroom) {
        this.classroom = classroom;
        this.subjectReports = new HashMap<>();
    }

    public Year getYear() { return classroom.getYear(); }

    public SchoolStage getStage() { return classroom.getStage(); }

    public int getGrade() { return classroom.getGrade(); }

    public SubjectReportCard getSubjectReportCard(Subject subject) {
        return subjectReports.get(subject);
    }

    public List<SubjectReportCard> getAllSubjectReportCards() {
        return new ArrayList<>(subjectReports.values());
    }

    public void addSubjectReportCard(Subject subject, SubjectReportCard subjectReportCard) {
        subjectReports.put(subject, subjectReportCard);
    }

    public void removeSubjectReportCard(Subject subject) {
        subjectReports.remove(subject);
    }

    public void editSubjectReportCard(Subject subject, SubjectReportCard subjectReportCard) {
        subjectReports.put(subject, subjectReportCard);
    }
}
