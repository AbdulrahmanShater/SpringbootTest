package com.example.springboottest.service;


import com.example.springboottest.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO save(CustomerDTO customer);
}
