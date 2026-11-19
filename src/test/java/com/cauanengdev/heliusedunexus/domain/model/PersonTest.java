package com.cauanengdev.heliusedunexus.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonTest {
    Address address;

    @BeforeEach
    public void setUp() {
        address = new Address("12345000", "012", "Main st", "Belforoxo", "Cityville", "CthuluState");
    }

    @Test
    public void should_personAttributes_when_personWithLastNameAndIdNotInConstructor() {
        Person person = new Person("John", Optional.of("Doe"), "1234567890",
                LocalDate.of(1990, 1, 1), "email.teste@gmail.com", address) {};

        assertThat(person.getFullName()).isEqualTo("John Doe");
        assertThat(person.getPhone()).isEqualTo("1234567890");
        assertThat(person.getEmail()).isEqualTo("email.teste@gmail.com");
        assertThat(person.getAddress()).isEqualTo(address);
        assertThat(person.getBirthday()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(person.getPersonId()).isNotNull();
    }

    @Test
    public void should_personAttributes_when_personWithoutLastNameAndIdInConstructor() {
        UUID uuid = UUID.randomUUID();
        Person person = new Person(uuid, "Julius", Optional.empty(), "9985461237",
                LocalDate.of(2000, 12, 25), "email.teste2@gmail.com", address) {};

        assertThat(person.getFullName()).isEqualTo("Julius");
        assertThat(person.getPhone()).isEqualTo("9985461237");
        assertThat(person.getEmail()).isEqualTo("email.teste2@gmail.com");
        assertThat(person.getAddress().city()).isEqualTo("Cityville");
        assertThat(person.getBirthday()).isEqualTo(LocalDate.of(2000, 12, 25));
        assertThat(person.getPersonId().id()).isEqualTo(uuid);
    }

    @Test
    public void should_updatedFullName_when_personCallsSetName() {
        Person per = new Person("Aliana", Optional.empty(), "5501234567",
                LocalDate.of(1990, 1, 1), "ariana.grande@gmail.com", address) {
        };

        assertThat(per.getFullName()).isEqualTo("Aliana");
        assertThat(per.getLastName()).isEmpty();

        per.setName("Ariana", "Grande");
        assertThat(per.getFullName()).isEqualTo("Ariana Grande");
        assertThat(per.getLastName()).isNotEmpty();
    }

    @Test
    public void should_trueInPersonEquals_when_personsHaveSameId() {
        UUID uuid = UUID.randomUUID();
        Person person1 = new Person(uuid, "Alice", Optional.of("Smith"), "1234567890",
                LocalDate.of(1990, 9, 1), "alice.smith@gmail.com", address) {};

        Person person2 = new Person(uuid, "Bob", Optional.of("Johnson"), "0987654321",
                LocalDate.of(1985, 6, 15), "bob.johnson@gmail.com", address) {};

        assertThat(person1.equals(person2)).isTrue();
    }

    @Test
    public void should_falseInPersonEquals_when_personsHaveDifferentIds() {
        Person person1 = new Person("Henry", Optional.of("Cavill"), "1234567890",
                LocalDate.of(1983, 5, 5), "henry.cavill@gmail.com", address) {};

        Person person2 = new Person("Jhony", Optional.of("Depth"), "0987654321",
                LocalDate.of(1945, 6, 20), "john.depth@gmail.com", address) {};

        assertThat(person1.equals(person2)).isFalse();
    }

    @Test
    public void should_falseInPersonEquals_when_comparedWithNull() {
        Person person = new Person("Jensen", Optional.of("Huang"), "1234567890",
                LocalDate.of(1963, 2, 17), "jensenhuang2026@protonmail.com", address) {};

        assertThat(person.equals(null)).isFalse();
    }

    @Test
    public void should_falseInPersonEquals_when_comparedWithOtherType() {
        Person person = new Person("Taylon", Optional.of("Luis"), "75924242424",
                LocalDate.of(2003, 2, 17), "luistaylon027@gmail.com", address) {};

        assertThat(person.equals("Taylon")).isFalse();
    }

    @Test
    public void should_trueInPersonEquals_when_comparedWithItself() {
        Person person = new Person("Yasmim", Optional.of("De Paula"), "1234567890",
                LocalDate.of(2005, 9, 10), "yasmimdepau@gmail.com", address) {};

        assertThat(person.equals(person)).isTrue();
    }

// hashCode

    @Test
    public void should_sameHashCode_when_personsHaveSameId() {
        UUID uuid = UUID.randomUUID();
        Person person1 = new Person(uuid, "Henry", Optional.of("Cavill"), "1234567890",
                LocalDate.of(1983, 5, 5), "henry.cavill@gmail.com", address) {};

        Person person2 = new Person(uuid, "Jensen", Optional.of("Huang"), "1234567890",
                LocalDate.of(1963, 2, 17), "jensenhuang2026@protonmail.com", address) {};

        assertThat(person1.hashCode()).isEqualTo(person2.hashCode());
    }

    @Test
    public void should_findPersonInHashSet_when_anotherPersonHasSameId() {
        UUID uuid = UUID.randomUUID();
        Set<Person> persons = new HashSet<>();
        Person person1 = new Person(uuid, "Sherlock", Optional.of("Holmes"), "1111111111",
                LocalDate.of(1854, 1, 6), "sherlock.holmes@teste.com", address) {
        };

        Person person2 = new Person(uuid, "Hermione", Optional.of("Granger"), "2222222222",
                LocalDate.of(1979, 9, 19), "hermione.granger@teste.com", address) {

        };

        persons.add(person1);

        assertThat(persons.contains(person2)).isTrue();
    }

    @Test
    public void should_trueInHasId_when_sameId() {
        UUID uuid = UUID.randomUUID();
        Person person = new Person(uuid, "Tony", Optional.of("Stark"), "3333333333",
                LocalDate.of(1970, 5, 29), "tony.stark@teste.com", address) {
        };

        assertThat(person.hasId(new PersonId(uuid))).isTrue();
    }

    @Test
    public void should_falseInHasId_when_differentId() {
        Person person = new Person("Elizabeth", Optional.of("Bennet"), "4444444444",
                LocalDate.of(1796, 3, 12), "elizabeth.bennet@teste.com", address) {
        };

        assertThat(person.hasId(new PersonId(UUID.randomUUID()))).isFalse();
    }
}
