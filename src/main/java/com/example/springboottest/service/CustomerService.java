package com.example.springboottest.service;


import com.example.springboottest.dto.CustomerDTO;
import com.example.springboottest.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO save(CustomerDTO customer);
    List<CustomerResponseDTO> getCustomers();
    CustomerResponseDTO getCustomerById(Long id);

}
