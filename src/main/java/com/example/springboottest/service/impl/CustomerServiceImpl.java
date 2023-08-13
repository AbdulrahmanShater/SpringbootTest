package com.example.springboottest.service.impl;

import com.example.springboottest.dto.AddressDTO;
import com.example.springboottest.dto.CustomerDTO;
import com.example.springboottest.dto.CustomerResponseDTO;
import com.example.springboottest.exception.EntityNotFoundException;
import com.example.springboottest.model.*;
import com.example.springboottest.repository.*;
import com.example.springboottest.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    final AddressRepository addressRepository;
    final AddressTypeRepository addressTypeRepository;
    final CityRepository cityRepository;
    final CountryRepository countryRepository;
    final CustomerRepository customerRepository;
    final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, AddressRepository addressRepository, AddressTypeRepository addressTypeRepository, CityRepository cityRepository, CountryRepository countryRepository) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.addressRepository = addressRepository;
        this.addressTypeRepository = addressTypeRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public CustomerResponseDTO save(CustomerDTO customer) {
        try {
            CustomerResponseDTO c = modelMapper.map(customerRepository.save(modelMapper.map(customer, Customer.class)), CustomerResponseDTO.class);
            c.setAddresses(new ArrayList<>());
            return c;
        } catch (DataIntegrityViolationException ex) {
            if (Objects.requireNonNull(ex.getMessage()).contains("uk_phoneNumber")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "phone number is exist", ex);
            } else if (Objects.requireNonNull(ex.getMessage()).contains("uk_email")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email is exist", ex);
            }
            throw ex;
        }
    }

    @Override
    public List<CustomerResponseDTO> getCustomers() {
        return customerRepository.findAll().stream().map(customer -> modelMapper.map(customer, CustomerResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        return modelMapper.map(customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found")), CustomerResponseDTO.class);
    }

    @Override
    public CustomerResponseDTO addAddress(Long id, AddressDTO addressDto) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        AddressType addressType = addressTypeRepository.findByName(addressDto.addressType).orElseThrow(() -> new EntityNotFoundException("address type not found"));
        Country country = countryRepository.findByName(addressDto.country).orElseThrow(() -> new EntityNotFoundException("country not found"));
        City city = cityRepository.findByCountry_idAndName(country.getId(), addressDto.city).orElseThrow(() -> new EntityNotFoundException("city not found"));
        Address address = new Address();
        address.setAddressLine(addressDto.addressLine);
        address.setCity(city);
        address.setAddressType(addressType);
        addressRepository.save(address);
        address.setCustomer(customer);
        customerRepository.save(customer);
        return modelMapper.map(customer, CustomerResponseDTO.class);
    }

}
