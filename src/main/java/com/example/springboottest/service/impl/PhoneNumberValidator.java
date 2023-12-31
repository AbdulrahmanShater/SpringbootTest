package com.example.springboottest.service.impl;

import com.example.springboottest.annotation.ValidPhoneNumber;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    @Override
    public boolean isValid(String phoneNumberStr, ConstraintValidatorContext context) {

        try {
            if (phoneNumberStr.matches(".*[a-zA-Z].*")) {
                return false;
            }
            PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(phoneNumberStr, null);

            return phoneNumber != null && phoneNumberUtil.isValidNumber(phoneNumber);

        } catch (NumberParseException ex) {
            return false;
        }
    }
}