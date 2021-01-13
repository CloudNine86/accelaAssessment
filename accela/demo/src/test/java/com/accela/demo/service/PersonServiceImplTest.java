package com.accela.demo.service;

import com.accela.demo.entity.Person;
import com.accela.demo.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class PersonServiceImplTest {

    PersonService personService;

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        personService = new PersonServiceImpl(personRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listPersons() {
        Person person = new Person();
        person.setId(1L);
        List<Person> list = new ArrayList<>();
        list.add(person);

        when(personRepository.findAll()).thenReturn(list);

        List retrievedPersons = personService.listPersons();

        assertNotNull(retrievedPersons,"Empty list of Persons");
        verify(personRepository, times(1)).findAll();
    }

    @Test
    void countPersons() {
    }

    @Test
    void addPerson() {
    }

    @Test
    void editPerson() {
    }

    @Test
    void deletePerson() {
    }

    @Test
    void addAddress() {
    }
}