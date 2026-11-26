package com.cauanengdev.heliusedunexus.domain.model;

public enum SchoolStage {
    KINDERGARTEN(1, 3),
    ELEMENTARY(1, 5),
    MIDDLE_SCHOOL(6, 9),
    HIGH_SCHOOL(1, 3);

    private final int minGrade;
    private final int maxGrade;

    SchoolStage(int minGrade, int maxGrade) {
        this.minGrade = minGrade;
        this.maxGrade = maxGrade;
    }

    public boolean isValidGrade(int grade) {
        return grade >= minGrade && grade <= maxGrade;
    }
}
