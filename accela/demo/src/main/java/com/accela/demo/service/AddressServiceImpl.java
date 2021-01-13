package com.accela.demo.service;

import com.accela.demo.entity.Address;
import com.accela.demo.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AddressServiceImpl implements AddressService {

    public final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address addAddress(String street, String city, String state, String postalCode) {
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setPostalCode(postalCode);
        Address savedAddress = addressRepository.save(address);
        return savedAddress;
    }

    @Override
    public Address editAddress(Long id, String street, String city, String state, String postalCode) throws NoSuchElementException {
        Address address = addressRepository.findById(id).get();
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setPostalCode(postalCode);
        Address editAddress = addressRepository.save(address);
        return editAddress;
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
