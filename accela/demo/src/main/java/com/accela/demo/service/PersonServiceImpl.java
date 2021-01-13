package com.accela.demo.service;

import com.accela.demo.entity.Address;
import com.accela.demo.entity.Person;
import com.accela.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    AddressService addressService;

    public final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> listPersons() {
        List<Person> personSet = new ArrayList<>();
        personRepository.findAll().iterator().forEachRemaining(personSet::add);
        return personSet;
    }

    @Override
    public Long countPersons() {
        return personRepository.count();
    }

    @Override
    public Person addPerson(String firstName, String lastName) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        Person savedPerson = personRepository.save(person);
        return savedPerson;
    }

    @Override
    public Person editPerson(Long id, String firstName, String lastName) {
        Person person;
        try {
           person = personRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            return null;
        }
        person.setFirstName(firstName);
        person.setLastName(lastName);
        return personRepository.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Person addAddress(Long personId, String street, String city, String state, String postalCode) throws NoSuchElementException {

        Person person = personRepository.findById(personId).get();
        Address address = addressService.addAddress(street, city, state, postalCode);
        person.setAddress(address);
        return personRepository.save(person);
    }
}
