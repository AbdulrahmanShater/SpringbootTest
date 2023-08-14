package com.example.springboottest.controller;

import com.example.springboottest.dto.AddressDTO;
import com.example.springboottest.dto.CustomerDTO;
import com.example.springboottest.dto.DataResponse;
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
    public ResponseEntity<DataResponse<CustomerDTO>> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataResponse<>("success", "Customer created successfully.", customerService.save(customerDTO)));
    }

    @GetMapping
    public ResponseEntity<DataResponse<List<CustomerDTO>>> getCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>("success", "Customers retrieved successfully.", customerService.getCustomers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<CustomerDTO>> getCustomer(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>("success", "Customer retrieved successfully.", customerService.getCustomerById(id)));
    }

    @PostMapping("/{id}/address")
    public ResponseEntity<DataResponse<CustomerDTO>> addCustomerAddress(@PathVariable Long id, @Valid @RequestBody AddressDTO addressDto) {
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>("success", "Address added successfully.", customerService.addAddress(id, addressDto)));
    }

    @DeleteMapping("/{id}/address/{addressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable Long id, @PathVariable Long addressId) {
        customerService.deleteAddress(id, addressId);
    }

}
