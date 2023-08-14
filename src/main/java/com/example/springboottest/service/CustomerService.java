package com.example.springboottest.service;


import com.example.springboottest.dto.AddressDTO;
import com.example.springboottest.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO save(CustomerDTO customer);

    List<CustomerDTO> getCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO addAddress(Long id, AddressDTO addressDto);

    void deleteAddress(Long id, Long addressId);

    List<CustomerDTO> getCustomersByCity(String name);
    List<CustomerDTO> getCustomersByPhone(String prefix);


}
