package com.example.springboottest.service.impl;

import com.example.springboottest.dto.CustomerDTO;
import com.example.springboottest.model.Customer;
import com.example.springboottest.repository.CustomerRepository;
import com.example.springboottest.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    final
    CustomerRepository customerRepository;
    final
    ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerDTO save(CustomerDTO customer) {
        return modelMapper.map(customerRepository.save(modelMapper.map(customer, Customer.class)), CustomerDTO.class);

    }


}
