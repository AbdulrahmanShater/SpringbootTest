package com.example.springboottest.service;


import com.example.springboottest.dto.AddressDTO;
import com.example.springboottest.dto.CustomerDTO;
import com.example.springboottest.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO save(CustomerDTO customer);

    List<CustomerResponseDTO> getCustomers();

    CustomerResponseDTO getCustomerById(Long id);

    CustomerResponseDTO addAddress(Long id, AddressDTO addressDto);

    String deleteAddress(Long id, Long addressId);

    List<CustomerResponseDTO> getCustomersByCity(String name);


}
