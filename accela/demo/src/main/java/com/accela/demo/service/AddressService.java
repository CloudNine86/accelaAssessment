package com.accela.demo.service;

import com.accela.demo.entity.Address;

public interface AddressService {

    public Address addAddress(String street, String city, String state, String postalCode);
    public Address editAddress(Long id, String street, String city, String state, String postalCode);
    public void deleteAddress(Long id);

}
