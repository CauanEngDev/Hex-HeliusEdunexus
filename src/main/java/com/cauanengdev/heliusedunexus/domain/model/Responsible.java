package com.cauanengdev.heliusedunexus.domain.model;

import java.util.List;

public interface Responsible {
    List<Student> getWards();
    void addWard(Student ward);
    void removeWard(PersonId wardId);
    void switchWard(Student ward, Responsible newResponsible);
}
