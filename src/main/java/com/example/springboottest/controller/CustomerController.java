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
public class CustomerController {
    final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public ResponseEntity<DataResponse<CustomerDTO>> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataResponse<>("success", "Customer created successfully.", customerService.save(customerDTO)));
    }

    @GetMapping("/customer")
    public ResponseEntity<DataResponse<List<CustomerDTO>>> getCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>("success", "Customers retrieved successfully.", customerService.getCustomers()));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<DataResponse<CustomerDTO>> getCustomer(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>("success", "Customer retrieved successfully.", customerService.getCustomerById(id)));
    }

    @PostMapping("/customer/{id}/address")
    public ResponseEntity<DataResponse<CustomerDTO>> addCustomerAddress(@PathVariable Long id, @Valid @RequestBody AddressDTO addressDto) {
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>("success", "Address added successfully.", customerService.addAddress(id, addressDto)));
    }

    @DeleteMapping("/customer/{id}/address/{addressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable Long id, @PathVariable Long addressId) {
        customerService.deleteAddress(id, addressId);
    }

    @GetMapping("city/{name}")
    public List<CustomerDTO> getCustomerByCities(@PathVariable String name) {
        return customerService.getCustomersByCity(name);
    }

    @GetMapping("phone/{prefix}")
    public List<CustomerDTO> getByPhone(@PathVariable String prefix) {
        return customerService.getCustomersByPhone(prefix);
    }
}
