package com.example.springboottest.service;

import com.example.springboottest.annotation.ValidPhoneNumber;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    @Override
    public boolean isValid(String phoneNumberStr, ConstraintValidatorContext context) {
        // Implement your phone number validation logic here
        // Return true if valid, false if not
        // You can use regular expressions or external libraries like libphonenumber
        // Example validation logic (simple regex-based validation):
        try {
            PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(phoneNumberStr, null);
            return phoneNumber != null && phoneNumberUtil.isValidNumber(phoneNumber);
        } catch (NumberParseException ex) {

            return false;
        }
    }
}