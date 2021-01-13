package com.accela.demo.service;

import com.accela.demo.entity.Address;
import com.accela.demo.entity.Person;

import java.util.List;

public interface PersonService {

    public List<Person> listPersons();
    public Long countPersons();
    public Person addPerson(String firstName, String lastName);
    public Person editPerson(Long id, String firstName, String lastName);
    public void deletePerson(Long id);
    public Person addAddress(Long personId, String street, String city, String state, String postalCode);

}
