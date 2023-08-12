package com.example.springboottest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddressDTO {
    @NotBlank(message = "addressType is required")
    public String addressType;
    @NotBlank(message = "city is required")
    public String city;
    @NotBlank(message = "country is required")
    public String country;
    @NotBlank(message = "addressLine is required")
    public String addressLine;
}
