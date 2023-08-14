package com.example.springboottest.service.impl;

import com.example.springboottest.dto.AddressDTO;
import com.example.springboottest.dto.CustomerDTO;
import com.example.springboottest.exception.EntityNotFoundException;
import com.example.springboottest.model.*;
import com.example.springboottest.repository.*;
import com.example.springboottest.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    final AddressRepository addressRepository;
    final AddressTypeRepository addressTypeRepository;
    final CityRepository cityRepository;
    final CountryRepository countryRepository;
    final CustomerRepository customerRepository;
    final ModelMapper modelMapper;
    @Autowired
    private EntityManager entityManager;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, AddressRepository addressRepository, AddressTypeRepository addressTypeRepository, CityRepository cityRepository, CountryRepository countryRepository) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.addressRepository = addressRepository;
        this.addressTypeRepository = addressTypeRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    @Transactional
    public CustomerDTO save(CustomerDTO customerDTO) {
        List<Address> addresses = new ArrayList<>();
        try {
            if (!customerDTO.getAddresses().isEmpty()) {
                for (AddressDTO addressDTO : customerDTO.getAddresses()) {
                    Address address = getAddressFromDTO(addressDTO);
                    addressRepository.save(address);
                    addresses.add(address);
                }
            }
        } catch (NullPointerException ignored) {
        }
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer.setAddresses(addresses);
        return modelMapper.map(customerRepository.save(customer), CustomerDTO.class);

    }

    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll().stream().map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return modelMapper.map(customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found")), CustomerDTO.class);
    }

    @Override
    public CustomerDTO addAddress(Long id, AddressDTO addressDto) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        Address address = getAddressFromDTO(addressDto);
        customer.AddAddress(address);
        customerRepository.save(customer);
        return modelMapper.map(customer, CustomerDTO.class);
    }

    @Override
    @Transactional
    public void deleteAddress(Long id, Long addressId) {
        customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        if (addressRepository.deleteByIdAndCustomer_id(addressId, id) == 0) {
            throw new EntityNotFoundException("Address not found Or Address not belongs for this Customer");
        }

    }

    @Override
    public List<CustomerDTO> getCustomersByCity(String name) {
        return customerRepository.findCustomersByCity(name).stream().map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getCustomersByPhone(String prefix) {
        return customerRepository.findByPhoneNumberStartsWith(prefix).stream().map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());
    }

    private Address getAddressFromDTO(AddressDTO addressDTO) {

        AddressType addressType = addressTypeRepository.findByNameIgnoreCase(addressDTO.addressType).orElseThrow(() -> new EntityNotFoundException("AddressType not found"));
        Country country = countryRepository.findByNameIgnoreCase(addressDTO.country).orElseThrow(() -> new EntityNotFoundException("Country not found"));
        City city = cityRepository.findByCountry_idAndNameIgnoreCase(country.getId(), addressDTO.city).orElseThrow(() -> new EntityNotFoundException("City not found"));
        Address address = new Address();
        address.setAddressLine(addressDTO.addressLine);
        address.setCity(city);
        address.setAddressType(addressType);
        return address;
    }

}
