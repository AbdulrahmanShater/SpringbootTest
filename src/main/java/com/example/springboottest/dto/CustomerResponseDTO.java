package com.example.springboottest.dto;

import com.example.springboottest.annotation.ValidPhoneNumber;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class CustomerResponseDTO extends CustomerDTO {
    private List<AddressDTO> addresses = new ArrayList<>();
}
