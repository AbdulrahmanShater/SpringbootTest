package com.example.springboottest.controller;

import com.example.springboottest.dto.CustomerDTO;
import com.example.springboottest.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    final
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerDTO createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        return customerService.save(customerDTO);
    }


}
