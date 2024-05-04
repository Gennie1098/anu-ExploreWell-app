package com.anu.gp24s1;

import org.junit.Test;
import static org.junit.Assert.*;

import com.anu.gp24s1.utils.Validator;

public class AuthenticationUnitTest {
    @Test
    public void userNameValidation_NullUserName() {
        assertFalse(Validator.isUserNameValid(null));
    }

    @Test
    public void userNameValidation_EmptyUserName() {
        assertFalse(Validator.isUserNameValid(""));
    }

    @Test
    public void userNameValidation_InvalidEmailUserName() {
        assertFalse(Validator.isUserNameValid("invalidEmail@"));
    }

    @Test
    public void userNameValidation_ValidEmailUserName() {
        assertTrue(Validator.isUserNameValid("validEmail@example.com"));
    }

    @Test
    public void userNameValidation_ValidNonEmailUserName() {
        assertTrue(Validator.isUserNameValid("validUserName"));
    }

    @Test
    public void passwordValidation_Null(){
        assertFalse(Validator.isPasswordValid(null));
    }

    @Test
    public void passwordValidation_Empty(){
        assertFalse(Validator.isPasswordValid(""));
    }

    @Test
    public void passwordValidation_InvalidPassword(){
        assertFalse(Validator.isPasswordValid("23"));
    }

    @Test
    public void passwordValidation_ValidPassword(){
        assertTrue(Validator.isPasswordValid("123456"));
    }

    @Test
    public void passwordValidation_ValidScapePassword(){
        assertTrue(Validator.isPasswordValid("123456 "));
    }
}
