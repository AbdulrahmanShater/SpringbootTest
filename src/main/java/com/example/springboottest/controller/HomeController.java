package com.example.springboottest.controller;

import com.example.springboottest.dto.CustomerResponseDTO;
import com.example.springboottest.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    final
    CustomerService customerService;

    public HomeController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("city/{name}")
    public List<CustomerResponseDTO> getCustomerByCities(@PathVariable String name) {
        return customerService.getCustomersByCity(name);
    }

    @GetMapping("phone/{prefix}")
    public List<CustomerResponseDTO> getByPhone(@PathVariable String prefix) {
        return customerService.getCustomersByPhone(prefix);
    }
}
