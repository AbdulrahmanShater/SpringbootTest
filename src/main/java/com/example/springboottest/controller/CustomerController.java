package com.example.springboottest.controller;

import com.example.springboottest.dto.AddressDTO;
import com.example.springboottest.dto.CustomerDTO;
import com.example.springboottest.dto.CustomerResponseDTO;
import com.example.springboottest.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerResponseDTO createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        return customerService.save(customerDTO);
    }

    @GetMapping
    public List<CustomerResponseDTO> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/{id}/address")
    public CustomerResponseDTO addCustomerAddress(@PathVariable Long id, @Valid @RequestBody AddressDTO addressDto) {
        return customerService.addAddress(id, addressDto);
    }

    @DeleteMapping("/{id}/address/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id, @PathVariable Long addressId) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.deleteAddress(id, addressId));
    }

}
