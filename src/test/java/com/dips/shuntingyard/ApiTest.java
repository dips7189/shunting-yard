package com.dips.shuntingyard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static com.dips.shuntingyard.api.Api.getAssociativity;
import static com.dips.shuntingyard.api.Api.getPrecedence;
import static com.dips.shuntingyard.api.Api.isFuncArgSeparator;
import static com.dips.shuntingyard.api.Api.isFunction;
import static com.dips.shuntingyard.api.Api.isLeftParentheses;
import static com.dips.shuntingyard.api.Api.isOperator;
import static com.dips.shuntingyard.api.Api.isRealNumber;
import static com.dips.shuntingyard.api.Api.isRightParentheses;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ApiTest {

    @Test
    public void testIsFunction() {
        assertEquals(true, isFunction("sin"));
        assertEquals(true, isFunction("cos"));
        assertEquals(true, isFunction("tan"));
        assertEquals(true, isFunction("e"));
        assertEquals(true, isFunction("log"));
        assertEquals(false, isFunction("invSin"));
    }

    @Test
    public void testIsOperator() {
        assertEquals(true, isOperator("^"));
        assertEquals(true, isOperator("/"));
        assertEquals(true, isOperator("x"));
        assertEquals(true, isOperator("+"));
        assertEquals(true, isOperator("-"));
        assertEquals(true, isOperator("%"));
        assertEquals(false, isOperator("$"));
    }

    @Test
    public void testIsRealNumber() {
        assertEquals(true, isRealNumber("5.6"));
        assertEquals(false, isRealNumber("xiv"));
    }

    @Test(expected = NotImplementedException.class)
    public void testIsFuncArgSeparator() {
        isFuncArgSeparator(",");
    }

    @Test
    public void testGetPrecedence() {
        assertEquals(4, getPrecedence("^"));
        assertEquals(3, getPrecedence("/"));
        assertEquals(3, getPrecedence("x"));
        assertEquals(2, getPrecedence("+"));
        assertEquals(2, getPrecedence("-"));
        assertEquals(5, getPrecedence("%"));
    }

    @Test
    public void testGetAssociativity() {
        assertEquals(0, getAssociativity("^"));
        assertEquals(1, getAssociativity("/"));
        assertEquals(1, getAssociativity("x"));
        assertEquals(1, getAssociativity("+"));
        assertEquals(1, getAssociativity("-"));
        assertEquals(1, getAssociativity("%"));
    }

    @Test
    public void testIsLeftParentheses() {
        assertEquals(true, isLeftParentheses("("));
        assertEquals(false, isLeftParentheses(")"));
    }

    @Test
    public void testIsRightParentheses() {
        assertEquals(true, isRightParentheses(")"));
        assertEquals(false, isRightParentheses("("));
    }

}