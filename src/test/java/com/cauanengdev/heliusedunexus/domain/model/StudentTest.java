package com.cauanengdev.heliusedunexus.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes Rigorosos da Entidade Student")
class StudentTest {

    private Address mockAddress;
    private Responsible mockResponsible;

    @BeforeEach
    void setUp() {
        mockAddress = new Address("Rua das Flores", "123", "Centro", "Cidade Exemplo", "SP", "12345-000");

        // Mock simples de Responsible (como Teacher ou Guardian implementam a interface)
        mockResponsible = new Guardian(
                "Maria",
                "Silva",
                "99999-8888",
                LocalDate.of(1980, 1, 1),
                "maria@email.com",
                mockAddress
        );
    }

    private Classroom createClassroom(int year, int grade) {
        Classroom classroom = new Classroom("A", Year.of(year), grade, 30, SchoolStage.ELEMENTARY);
        return classroom;
    }

    private Teacher createTeacher(String firstName) {
        Address address = new Address("12345000", "012", "Main st", "Belforoxo", "Cityville", "CthuluState");
        return new Teacher(UUID.randomUUID(), firstName, "Teste", "1111111111",
                LocalDate.of(1980, 1, 1), firstName.toLowerCase() + "@teste.com", address, AcademicDegree.BACHELOR);
    }

    @Test
    @DisplayName("Deve criar estudante com sucesso usando o construtor sem ID")
    void should_createStudentWithoutIdSuccessfully_when_constructorWithoutIdIsCalled() {
        // Act
        Student student = new Student(
                "Ana",
                "Souza",
                "98888-1111",
                LocalDate.of(2010, 5, 15),
                "ana.souza@student.com",
                mockAddress,
                mockResponsible
        );

        // Assert
        assertNotNull(student);
        assertNotNull(student.getPersonId(), "O ID herdado de Person deve ser gerado automaticamente");
        assertEquals("Ana", student.getFirstName());
        assertEquals("Souza", student.getLastName().orElse(null));
        assertEquals("ana.souza@student.com", student.getEmail());
        assertEquals(mockResponsible, student.getResponsible());
    }

    @Test
    @DisplayName("Deve criar estudante com sucesso usando o construtor com ID customizado")
    void should_createStudentWithCustomIdSuccessfully_when_constructorWithCustomIdIsCalled() {
        // Arrange
        UUID customUuid = UUID.randomUUID();
        PersonId customPersonId = new PersonId(customUuid);

        // Act
        Student student = new Student(
                customUuid,
                "Carlos",
                "Oliveira",
                "97777-2222",
                LocalDate.of(2008, 10, 20),
                "carlos@student.com",
                mockAddress,
                mockResponsible
        );

        // Assert
        assertNotNull(student);
        assertEquals(customPersonId, student.getPersonId());
        assertEquals("Carlos", student.getFirstName());
        assertEquals("Oliveira", student.getLastName().orElse(null));
    }

    @Test
    @DisplayName("Deve gerenciar boletins anuais (YearReportCard) e recuperar o atual corretamente")
    void should_manageReportCardsSuccessfully_when_addAndRemoveReportCardsAreCalled() {
        // Arrange
        Student student = new Student(
                "Lucas",
                "Lima",
                "96666-3333",
                LocalDate.of(2012, 3, 10),
                "lucas@student.com",
                mockAddress,
                mockResponsible
        );

        Year year2025 = Year.of(2025);
        Year year2026 = Year.of(2026);

        YearReportCard report2025 = new YearReportCard(createClassroom(2025, 5));
        YearReportCard report2026 = new YearReportCard(createClassroom(2026, 3));

        // Act & Assert - Adicionando
        student.addReportCard(year2025, report2025);
        student.addReportCard(year2026, report2026);

        assertEquals(2, student.getAllReportCards().size());
        assertEquals(report2026, student.getCurrentReportCard(), "O report card atual deve ser o do ano mais recente (TreeMap)");
        assertEquals(report2025, student.getReportCardByYear(year2025));

        // Act & Assert - Removendo
        student.removeReportCard(year2026);
        assertEquals(1, student.getAllReportCards().size());
        assertEquals(report2025, student.getCurrentReportCard(), "Após remover 2026, o atual deve voltar a ser 2025");
    }

    @Test
    @DisplayName("Deve permitir alterar o responsável pelo estudante (Responsible Setter)")
    void should_updateResponsibleSuccessfully_when_setResponsibleIsCalled() {
        // Arrange
        Student student = new Student(
                "Mariana",
                "Costa",
                "95555-4444",
                LocalDate.of(2011, 7, 7),
                "mariana@student.com",
                mockAddress,
                null
        );

        assertNull(student.getResponsible());

        // Act
        student.setResponsible(mockResponsible);

        // Assert
        assertEquals(mockResponsible, student.getResponsible());
    }
}