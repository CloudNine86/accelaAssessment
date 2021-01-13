package com.accela.demo;

import com.accela.demo.entity.Person;
import com.accela.demo.service.AddressService;
import com.accela.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.NoSuchElementException;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    PersonService personService;

    @Autowired
    AddressService addressService;

    private final String instructions = "Get infos by listing user with input 1." +
            "Enter command:\n" +
            "Input 1 to list all persons\n" +
            "2 to add a Person\n" +
            "3 to edit a Person\n" +
            "4 to delete a Person\n" +
            "5 to add an Address\n" +
            "6 to edit an Address\n" +
            "7 to delete an Address\n" +
            "8 to count all persons";

    private final String quit = "Write Quit to return to main menù";
    private final String addPersonInstruction = "Add a person: input \"first_name\" and \"last_name\" separated by a comma:";
    private final String editPersonInstruction = "Edit a person: input \"person_id\" \"first_name\" and \"last_name\" separated by a comma:";
    private final String deletePersonInstruction = "Delete a person: input \"person_id\"";
    private final String addAddressInstruction = "Add an address associated with a person: input \"person_id\" \"street\" \"city\" \"state\" and \"postal_code\" separated by a comma:";
    private final String editAddressInstruction = "Edit an address: input \"address_id\" \"street\" \"city\" \"state\" and \"postal_code\" separated by a comma:";
    private final String deleteAddressInstruction = "Delete an address: input \"address_id\"";

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Application started");
        System.out.println("------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        try {
            boolean insideCycle;
            while (true) {
                System.out.println(instructions);
                String line = scanner.nextLine();
                switch (line) {
                    case "1":
                        for (Person p : personService.listPersons()) {
                            System.out.println(p.toString());
                        }
                        break;

                    case "2":
                        insideCycle = true;
                        while (insideCycle) {
                            System.out.println(addPersonInstruction);
                            System.out.println(quit);
                            line = scanner.nextLine();
                            if (line.equalsIgnoreCase("Quit")) break;

                            String[] tokens = line.split(",");
                            if (tokens.length >= 2 && validateString(tokens[0]) && validateString(tokens[1])) {
                                personService.addPerson(tokens[0].trim(), tokens[1].trim());
                                insideCycle = false;
                            } else
                                System.out.println("Input invalid try again");
                        }
                        break;

                    case "3":
                        insideCycle = true;
                        while (insideCycle) {
                            System.out.println(editPersonInstruction);
                            System.out.println(quit);
                            line = scanner.nextLine();
                            if (line.equalsIgnoreCase("Quit")) break;
                            String[] tokens = line.split(",");
                            if (tokens.length >= 3 && validateLong(tokens[0].trim()) && validateString(tokens[1]) && validateString(tokens[1])) {
                                Person editedPerson = personService.editPerson(Long.parseLong(tokens[0].trim()), tokens[1].trim(), tokens[2].trim());
                                if (editedPerson != null) {
                                    System.out.println(editedPerson);
                                    insideCycle = false;
                                } else {
                                    System.out.println("Person ID " + tokens[0] + " not found, try again: ");
                                    continue;
                                }
                            } else
                                System.out.println("Input invalid try again");
                        }
                        break;
                    case "4":
                        insideCycle = true;
                        while (insideCycle) {
                            System.out.println(deletePersonInstruction);
                            System.out.println(quit);
                            line = scanner.nextLine();
                            if (line.equalsIgnoreCase("Quit")) break;
                            if (validateLong(line)) {
                                try {
                                    personService.deletePerson(Long.parseLong(line.trim()));
                                    break;
                                } catch (EmptyResultDataAccessException ex) {
                                    System.out.println("Person ID " + line + " not found, try again: ");
                                }
                            } else {
                                System.out.println("Please insert a number");
                                continue;
                            }
                        }
                        break;

                    case "5":
                        insideCycle = true;
                        while (insideCycle) {
                            System.out.println(addAddressInstruction);
                            System.out.println(quit);
                            line = scanner.nextLine();
                            if (line.equalsIgnoreCase("Quit")) break;

                            String[] tokens = line.split(",");
                            if (tokens.length >= 5 && validateLong(tokens[0]) && validateString(tokens[1]) && validateString(tokens[2]) && validateString(tokens[3]) && validateString(tokens[4])) {
                                try {
                                    personService.addAddress(Long.parseLong(tokens[0].trim()), tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), tokens[4].trim());
                                    insideCycle = false;
                                } catch (NoSuchElementException ex) {
                                    System.out.println("Person ID " + Long.parseLong(tokens[0].trim()) + " not found, try again: ");
                                }
                            } else
                                System.out.println("Input invalid try again");
                        }
                        break;

                    case "6":
                        insideCycle = true;
                        while (insideCycle) {
                            System.out.println(editAddressInstruction);
                            System.out.println(quit);
                            line = scanner.nextLine();
                            if (line.equalsIgnoreCase("Quit")) break;

                            String[] tokens = line.split(",");
                            if (tokens.length >= 5 && validateLong(tokens[0]) && validateString(tokens[1]) && validateString(tokens[2]) && validateString(tokens[3]) && validateString(tokens[4])) {
                                try {
                                    addressService.editAddress(Long.parseLong(tokens[0].trim()), tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), tokens[4].trim());
                                    insideCycle = false;
                                } catch (NoSuchElementException ex) {
                                    System.out.println("Person ID " + Long.parseLong(tokens[0].trim()) + " not found, try again: ");
                                }
                            } else
                                System.out.println("Input invalid try again");
                        }
                        break;

                    case "7":
                        insideCycle = true;
                        while (insideCycle) {
                            System.out.println(deleteAddressInstruction);
                            System.out.println(quit);
                            line = scanner.nextLine();
                            if (line.equalsIgnoreCase("Quit")) break;
                            if (validateLong(line)) {
                                try {
                                    addressService.deleteAddress(Long.parseLong(line.trim()));
                                    break;
                                } catch (EmptyResultDataAccessException ex) {
                                    System.out.println("Address ID " + line + " not found, try again: ");
                                }
                            } else {
                                System.out.println("Please insert a number");
                                continue;
                            }
                        }
                        break;
                    case "8":
                        System.out.println("There are currently " + personService.countPersons() + " persons saved in the system!" );

                }
            }
        } catch (Exception e) {
            System.out.println("Unexpected exception, system shutdown.");
        }

    }


    private boolean validateLong(String l) {
        try {
            Long.parseLong(l);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }


    private boolean validateString(String s) {
        return s != null && !s.isEmpty() && !s.isBlank();
    }

}
