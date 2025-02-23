package com.unittestse.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StringProcessorServiceTest {

    @Autowired
    private StringProcessorService service;


    @Test
    public void testIsPalindrome_ValidPalindrome(){
        var result = service.isPalindrome("malayalam");
        Assertions.assertTrue(result);
    }


    @Test
    public void testIsPalindrome_InValidPalindrome(){
        var result = service.isPalindrome("not-palindrome");
        Assertions.assertFalse(result);
    }

    @Test
    public void testIsPalindrome_NullInput(){ // for handling null values gracefully we can use AssertThrows like below
        Assertions.assertThrows(NullPointerException.class, ()->{
         service.isPalindrome(null);
        });
    }

}
