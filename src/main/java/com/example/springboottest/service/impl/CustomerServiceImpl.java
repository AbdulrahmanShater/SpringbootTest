package com.example.springboottest.service.impl;

import com.example.springboottest.dto.CustomerDTO;
import com.example.springboottest.dto.CustomerResponseDTO;
import com.example.springboottest.model.Customer;
import com.example.springboottest.repository.CustomerRepository;
import com.example.springboottest.service.CustomerService;
import javassist.tools.web.BadHttpRequest;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Objects;

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
        try {
            CustomerResponseDTO c = modelMapper.map(customerRepository.save(modelMapper.map(customer, Customer.class)), CustomerResponseDTO.class);
            c.setAddresses(new ArrayList<>());
            return c;
        } catch (DataIntegrityViolationException ex) {
            if (Objects.requireNonNull(ex.getMessage()).contains("uk_phoneNumber")) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "phone number is exist", ex);
            } else if (Objects.requireNonNull(ex.getMessage()).contains("uk_email")) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "email is exist", ex);
            }
            throw ex;
        }
    }


}
