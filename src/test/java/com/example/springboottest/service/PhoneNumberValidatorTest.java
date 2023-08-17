package com.example.springboottest.service;

import com.example.springboottest.service.impl.PhoneNumberValidator;
import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ClockProvider;
import javax.validation.ConstraintValidatorContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PhoneNumberValidatorTest {

    @InjectMocks
    PhoneNumberValidator phoneNumberValidator;

    @Test
    @DisplayName("phone number valid")
    void isValid() {
        String phoneStr = "+971503131842";
        assertThat(phoneNumberValidator.isValid(phoneStr, null)).isTrue();
    }

    @Test
    @DisplayName("phone number invalid because it has letters")
    void isInValid_letters() {
        String phoneStr = "+97150abd3131842";
        assertThat(phoneNumberValidator.isValid(phoneStr, null)).isFalse();
    }
    @Test
    @DisplayName("phone number invalid because country doesnt have this number")
    void isInValid_country() {
        String phoneStr = "+971903131842";
        assertThat(phoneNumberValidator.isValid(phoneStr, null)).isFalse();
    }
    @Test
    @DisplayName("phone number invalid because country calling code")
    void isInValid_countryCode() {
        String phoneStr = "+99903131842";
        assertThat(phoneNumberValidator.isValid(phoneStr, null)).isFalse();
    }

}