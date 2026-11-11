package com.cauanengdev.heliusedunexus.domain.model;

import java.time.Year;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Student extends Person {
    private NavigableMap<Year, YearReportCard> reportCards;

    public Student() {
        reportCards = new TreeMap<>();
    }

    public YearReportCard getCurrentReportCard() { return reportCards.lastEntry().getValue(); }

    public YearReportCard getReportCardByYear(Year year) { return reportCards.get(year); }

    public void addReportCard(Year year, YearReportCard reportCard) { reportCards.put(year, reportCard); }

    public void removeReportCard(Year year) { reportCards.remove(year); }

    
}
