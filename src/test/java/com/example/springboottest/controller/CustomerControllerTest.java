package com.example.springboottest.controller;

import com.example.springboottest.dto.AddressDTO;
import com.example.springboottest.dto.CustomerDTO;
import com.example.springboottest.model.Address;
import com.example.springboottest.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private CustomerDTO customerDTO1;
    private CustomerDTO customerDTO2;

    @BeforeEach
    public void init() {

        customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName("Abdulrahman");
        customerDTO1.setLastName("Shater");
        customerDTO1.setPhoneNumber("+971503131842");
        customerDTO1.setEmail("abdulrahmanshatter@gmail.com");


        customerDTO2 = new CustomerDTO();
        customerDTO2.setFirstName("Example");
        customerDTO2.setLastName("lastName");
        customerDTO2.setPhoneNumber("+971503333333");
        customerDTO2.setEmail("info@example.com");

    }

    @Test
    @DisplayName("creating customer with successful and valid body")
    public void testCreateCustomer() throws Exception {

        when(customerService.save(any(CustomerDTO.class))).thenReturn(customerDTO1);

        mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"firstName\": \"Abdulrahman\",\n" +
                                "    \"lastName\": \"shater\",\n" +
                                "    \"phoneNumber\": \"+971503131842\",\n" +
                                "    \"email\": \"abdulrahmanshatter@gmail.com\",\n" +
                                "    \"addresses\": [\n" +
                                "        {\n" +
                                "            \"addressType\": \"Home\",\n" +
                                "            \"city\": \"Abu Dhabi\",\n" +
                                "            \"country\": \"uae\",\n" +
                                "            \"addressLine\": \"Mecca St\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"addressType\": \"work\",\n" +
                                "            \"city\": \"dubai\",\n" +
                                "            \"country\": \"uae\",\n" +
                                "            \"addressLine\": \"Jumeirah\"\n" +
                                "        }\n" +
                                "    ]\n" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.firstName").value("Abdulrahman"));
    }
    @Test
    @DisplayName("creating customer with not valid phone number")
    public void testCreateCustomer_phoneError() throws Exception {

        when(customerService.save(any(CustomerDTO.class))).thenReturn(customerDTO1);

        mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"firstName\": \"Abdulrahman\",\n" +
                                "    \"lastName\": \"shater\",\n" +
                                "    \"phoneNumber\": \"+971903131842\",\n" +
                                "    \"email\": \"abdulrahmanshatter@gmail.com\",\n" +
                                "    \"addresses\": [\n" +
                                "        {\n" +
                                "            \"addressType\": \"Home\",\n" +
                                "            \"city\": \"Abu Dhabi\",\n" +
                                "            \"country\": \"uae\",\n" +
                                "            \"addressLine\": \"Mecca St\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"addressType\": \"work\",\n" +
                                "            \"city\": \"dubai\",\n" +
                                "            \"country\": \"uae\",\n" +
                                "            \"addressLine\": \"Jumeirah\"\n" +
                                "        }\n" +
                                "    ]\n" +
                                "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[phoneNumber is invalid]"));
    }



    @Test
    @DisplayName("creating customer with empty firstName")
    public void testCreateCustomer_firstNameError() throws Exception {

        when(customerService.save(any(CustomerDTO.class))).thenReturn(customerDTO1);

        mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"lastName\": \"shater\",\n" +
                                "    \"phoneNumber\": \"+971503131842\",\n" +
                                "    \"email\": \"abdulrahmanshatter@gmail.com\",\n" +
                                "    \"addresses\": [\n" +
                                "        {\n" +
                                "            \"addressType\": \"Home\",\n" +
                                "            \"city\": \"Abu Dhabi\",\n" +
                                "            \"country\": \"uae\",\n" +
                                "            \"addressLine\": \"Mecca St\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"addressType\": \"work\",\n" +
                                "            \"city\": \"dubai\",\n" +
                                "            \"country\": \"uae\",\n" +
                                "            \"addressLine\": \"Jumeirah\"\n" +
                                "        }\n" +
                                "    ]\n" +
                                "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[firstname is required]"));
    }


    @Test
    @DisplayName("creating customer with invalid email")
    public void testCreateCustomer_emailError() throws Exception {

        when(customerService.save(any(CustomerDTO.class))).thenReturn(customerDTO1);

        mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"firstName\": \"Abdulrahman\",\n" +
                                "    \"lastName\": \"shater\",\n" +
                                "    \"phoneNumber\": \"+971503131842\",\n" +
                                "    \"email\": \"abdulrahmanshatter.com\",\n" +
                                "    \"addresses\": [\n" +
                                "        {\n" +
                                "            \"addressType\": \"Home\",\n" +
                                "            \"city\": \"Abu Dhabi\",\n" +
                                "            \"country\": \"uae\",\n" +
                                "            \"addressLine\": \"Mecca St\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"addressType\": \"work\",\n" +
                                "            \"city\": \"dubai\",\n" +
                                "            \"country\": \"uae\",\n" +
                                "            \"addressLine\": \"Jumeirah\"\n" +
                                "        }\n" +
                                "    ]\n" +
                                "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[email is invalid]"));
    }

    @Test
    @DisplayName("Should List All Customers When making GET request to endpoint - /customer")
    public void testGetCustomers() throws Exception {
        List<CustomerDTO> customers = Arrays.asList(customerDTO1, customerDTO2);

        when(customerService.getCustomers()).thenReturn(customers);

        mockMvc.perform(get("/customer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].firstName").value("Abdulrahman"))
                .andExpect(jsonPath("$.data[1].firstName").value("Example"));
    }

    @Test
    @DisplayName("Should Customer by it is Id When making GET request to endpoint - /customer/{id}")
    public void testGetCustomer() throws Exception {

        when(customerService.getCustomerById(1L)).thenReturn(customerDTO1);

        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.firstName").value("Abdulrahman"));
    }

    @Test
    @DisplayName("Should Address added successfully ")
    public void testAddCustomerAddress() throws Exception {
        AddressDTO addressDto = new AddressDTO();
        addressDto.setCity("aleppo");
        addressDto.setAddressType("pob");
        addressDto.setCountry("syria");
        addressDto.setAddressLine("Aljamiliah");
        List<AddressDTO> addresses=Arrays.asList(addressDto);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAddresses(addresses);

        when(customerService.addAddress(1L, addressDto)).thenReturn(customerDTO);

        mockMvc.perform(post("/customer/1/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"addressType\": \"pob\",\n" +
                                "    \"city\": \"aleppo\",\n" +
                                "    \"country\": \"syria\",\n" +
                                "    \"addressLine\": \"Aljamiliah\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Address added successfully."));

    }

    @Test
    @DisplayName("Delete address successfully from customer")
    public void testDeleteAddress() throws Exception {
        mockMvc.perform(delete("/customer/1/address/2"))
                .andExpect(status().isNoContent());
        verify(customerService, times(1)).deleteAddress(1L, 2L);
    }
}