package com.example.springboottest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data

public class CustomerResponseDTO extends CustomerDTO {
    private List<AddressDTO> addresses = new ArrayList<>();
}
