package com.cauanengdev.heliusedunexus.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da Entidade Guardian e contrato Responsible")
class GuardianTest {

    private Guardian newResponsible;
    private Guardian guardian;
    private Student dependentStudent;

    @BeforeEach
    void setUp() {
        newResponsible = new Guardian(
                "Carlos",
                "Silva (Pai)",
                "97777-4444",
                LocalDate.of(1980, 3, 15),
                "carlos@gmail.com",
                null
        );

        guardian = new Guardian(
                "Helena",
                "Silva (Mãe)",
                "96666-2222",
                LocalDate.of(1985, 6, 20),
                "helena@gmail.com",
                null
        );

        dependentStudent = new Student(
                "Pedro",
                "Silva",
                "95555-3333",
                LocalDate.of(2014, 8, 12),
                "pedro@student.com",
                null,
                guardian
        );
    }

    @Test
    @DisplayName("Deve associar aluno dependente (Ward) ao Guardian com sucesso")
    void should_addDependentWardToGuardian_when_addWardIsCalled() {
        // Act
        guardian.addWard(dependentStudent);

        // Assert
        List<Student> wards = guardian.getWards();
        assertEquals(1, wards.size());
        assertTrue(wards.contains(dependentStudent));
    }

    @Test
    @DisplayName("Deve remover tutelado pelo PersonId")
    void should_removeWardByPersonId_when_removeWardIsCalled() {
        guardian.addWard(dependentStudent);

        guardian.removeWard(dependentStudent.getPersonId());

        assertTrue(guardian.getWards().isEmpty());
    }

    @Test
    @DisplayName("Deve transferir tutelado para outro Responsible")
    void should_switchWardToNewResponsible_when_switchWardIsCalled() {
        guardian.addWard(dependentStudent);

        guardian.switchWard(dependentStudent, newResponsible);

        assertTrue(guardian.getWards().isEmpty());
        assertTrue(newResponsible.getWards().contains(dependentStudent));
    }
}