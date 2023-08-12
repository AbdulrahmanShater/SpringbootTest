package com.example.springboottest.controller;
import com.example.springboottest.dto.CustomerDTO;
import com.example.springboottest.model.Address;
import com.example.springboottest.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @PostMapping
    public String createCustomer(@RequestBody @Valid CustomerDTO customer) {
        return "add Customer ";
    }


}
