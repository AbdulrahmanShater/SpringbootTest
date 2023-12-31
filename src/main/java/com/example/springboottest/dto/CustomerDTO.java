package com.example.springboottest.dto;

import com.example.springboottest.annotation.ValidPhoneNumber;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data

public class CustomerDTO {
    @NotBlank(message = "firstname is required")
    private String firstName;
    @NotBlank(message = "lastName is required")
    private String lastName;

    @NotBlank(message = "phoneNumber is required")
    @ValidPhoneNumber(message = "phoneNumber is invalid")
    private String phoneNumber;
    @NotBlank(message = "email is required")
    @Email(message = "email is invalid")
    private String email;

    private List<AddressDTO> addresses ;

}
