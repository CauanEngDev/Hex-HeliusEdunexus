package com.cauanengdev.heliusedunexus.domain.model;

import com.cauanengdev.heliusedunexus.domain.exceptions.StudentAlreadyEnrolledException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;

public class Student extends Person {
    private NavigableMap<Year, YearReportCard> reportCards;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private Responsible responsible;

    public Student(String firstName, String lastName, String phone,
                   LocalDate birthday, String email, Address address,
                   Responsible responsible) {

        super(firstName, Optional.ofNullable(lastName), phone, birthday, email, address);
        this.reportCards = new TreeMap<>();
        this.responsible = responsible;
    }

    public Student(UUID id, String firstName, String lastName, String phone,
                   LocalDate birthday, String email, Address address,
                   Responsible responsible) {

        super(id, firstName, Optional.ofNullable(lastName), phone, birthday, email, address);
        this.reportCards = new TreeMap<>();
        this.responsible = responsible;
    }

    public YearReportCard getCurrentReportCard() { return reportCards.lastEntry().getValue(); }

    public YearReportCard getReportCardByYear(Year year) { return reportCards.get(year); }

    public void addReportCard(Year year, YearReportCard reportCard) {
        if (reportCards.containsKey(year)) {
            throw new StudentAlreadyEnrolledException(this.getPersonId(), year);
        }
        reportCards.put(year, reportCard);
    }

    public void removeReportCard(Year year) { reportCards.remove(year); }

    public List<YearReportCard> getAllReportCards() { return new ArrayList<>(reportCards.values()); }
}
