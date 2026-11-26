package com.cauanengdev.heliusedunexus.domain.model;

import com.cauanengdev.heliusedunexus.domain.exceptions.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Year;
import java.util.*;

@Getter(AccessLevel.PUBLIC)
public class Classroom {
    @Setter(AccessLevel.NONE)
    private ClassroomId classroomId;

    @Setter(AccessLevel.PUBLIC)
    private String section;

    @Setter(AccessLevel.NONE)
    private Year year;

    @Setter(AccessLevel.NONE)
    private int grade;

    @Setter(AccessLevel.NONE)
    private int capacity;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Map<Subject, Teacher> teachersBySubject;

    @Setter(AccessLevel.NONE)
    private SchoolStage stage;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Set<PersonId> studentIds;

    public Classroom(String section, Year year, int grade, int capacity, SchoolStage stage) {
        this.classroomId = new ClassroomId();
        this.section = section;
        this.year = year;
        this.capacity = capacity;
        this.teachersBySubject = new HashMap<>();
        this.studentIds = new HashSet<>();
        validateGradeAndStage(stage, grade);
    }

    public Classroom(UUID id, String section, Year year, int grade, int capacity, SchoolStage stage) {
        this.classroomId = new ClassroomId(id);
        this.section = section;
        this.year = year;
        this.capacity = capacity;
        this.teachersBySubject = new HashMap<>();
        this.studentIds = new HashSet<>();
        validateGradeAndStage(stage, grade);
    }

    public Set<PersonId> getStudentIds() {
        return new HashSet<>(studentIds);
    }

    public Map<Subject, Teacher> getTeachersAndSubjects() {
        return new HashMap<>(teachersBySubject);
    }

    public Teacher getTeacherBySubject(Subject subject) {
        return teachersBySubject.get(subject);
    }

    public BigDecimal getClassAverage(Subject subject, List<Student> students) {
        BigDecimal totalAverage = BigDecimal.ZERO;
        int studentCount = 0;

        for (Student student : students) {
            if (studentIds.contains(student.getPersonId())) {
                YearReportCard reportCard = student.getReportCardByYear(this.year);
                if (reportCard != null) {
                    BigDecimal subjectAverage = reportCard.getSubjectReportCard(subject).getAverageGrade();
                    if (subjectAverage != null) {
                        totalAverage = totalAverage.add(subjectAverage);
                        studentCount++;
                    }
                }
            }
        }

        return studentCount > 0 ? totalAverage.divide(BigDecimal.valueOf(studentCount), 2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
    }

    public void changeCapacity(int newCapacity) {
        if (newCapacity < studentIds.size() || newCapacity <= 0) {
            throw new InvalidCapacityException(newCapacity);
        }
        this.capacity = newCapacity;
    }

    public void addStudent(Student student) {
        if (studentIds.size() == capacity) {
            throw new ClassroomFullException(this.getClassroomId(), student.getPersonId());
        }
        studentIds.add(student.getPersonId());
    }

    public void removeStudent(Student student) {
        studentIds.remove(student.getPersonId());
    }

    public void switchStudent(Student studentToSwitch, Classroom newClassroom) {
        if (newClassroom.studentIds.size() == newClassroom.capacity ||
                !newClassroom.getYear().equals(this.getYear()) ||
                !this.getStudentIds().contains(studentToSwitch.getPersonId())) {

            throw new SwitchStudentForClassException(studentToSwitch.getPersonId(),
                                                     this.getClassroomId(),
                                                     this.getCapacity(),
                                                     newClassroom.getClassroomId(),
                                                     newClassroom.getCapacity()
            );
        }
        newClassroom.addStudent(studentToSwitch);
        studentIds.remove(studentToSwitch.getPersonId());
    }

    public void assignTeacherToSubject(Subject subject, Teacher teacher) {
        if (!this.hasSubject(subject) || this.teachersBySubject.get(subject).equals(teacher)) {
            throw new AssignTeacherForClassException(teacher.getPersonId(), this.getClassroomId(), subject);
        }
        if (!teacher.getQualifications().contains(subject)) {
            throw new TeacherNotQualifiedException(teacher.getPersonId(), subject);
        }

        teachersBySubject.put(subject, teacher);
    }

    public void changeGrade(SchoolStage newStage, int newGrade) {
        validateGradeAndStage(newStage, newGrade);
    }

    private void validateGradeAndStage(SchoolStage newStage, int newGrade) {
        if (!newStage.isValidGrade(newGrade)) {
            throw new InvalidGradeException(newGrade, newStage);
        }

        this.stage = newStage;
        this.grade = newGrade;
    }

    public boolean hasSubject(Subject subject) {
        return teachersBySubject.containsKey(subject);
    }

    public String getFullClassroomName() {
        return this.getStage() + " - " + this.getGrade() + "º " + this.getSection();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Classroom other) {
            return this.classroomId.equals(other.classroomId);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() { return this.classroomId.hashCode(); }

    public boolean hasId(ClassroomId id) {
        return this.classroomId.equals(id);
    }
}
