package com.dips.shuntingyard;

import com.dips.shuntingyard.exceptions.MismatchedParenthesesException;
import java.util.ArrayDeque;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ShuntingYardTest {

    private ShuntingYard shuntingYard;

    @Before
    public void setUp() {
        shuntingYard = new ShuntingYard();
    }

    @Test
    public void testExpressionOne() throws MismatchedParenthesesException {
        List<String> infixExpr = asList("1", "+", "2");
        ArrayDeque<String> expectedRPNExpr = new ArrayDeque<>(asList("1", "2", "+"));

        contains(shuntingYard.toRpn(infixExpr), expectedRPNExpr);
    }

    @Test
    public void testExpressionTwo() throws MismatchedParenthesesException {
        List<String> infixExpr = asList("5000", "x", "(", "33", "+", "4", ")");
        ArrayDeque<String> expectedRPNExpr = new ArrayDeque<>(asList("5000", "33", "4", "+", "x"));

        contains(shuntingYard.toRpn(infixExpr), expectedRPNExpr);
    }

    @Test
    public void testExpressionThree() throws MismatchedParenthesesException {
        List<String> infixExpr = asList("5000", "x", "sin", "(", "8", "+", "4", ")");
        ArrayDeque<String> expectedRPNExpr = new ArrayDeque<>(asList("5000", "8", "4", "+", "sin", "x"));

        contains(shuntingYard.toRpn(infixExpr), expectedRPNExpr);
    }

    @Test
    public void testExpressionFour() throws MismatchedParenthesesException {
        List<String> infixExpr = asList("5000", "x", "sin", "(", "cos", "(", "8", "+", "4", ")", ")");
        ArrayDeque<String> expectedRPNExpr = new ArrayDeque<>(asList("5000", "8", "4", "+", "cos", "sin", "x"));

        contains(shuntingYard.toRpn(infixExpr), expectedRPNExpr);
    }

    @Test
    public void testExpressionFive() throws MismatchedParenthesesException {
        List<String> infixExpr = asList("5000", "x", "sin", "(", "8", ")");
        ArrayDeque<String> expectedRPNExpression = new ArrayDeque<>(asList("5000", "8", "sin", "x"));

        contains(shuntingYard.toRpn(infixExpr), expectedRPNExpression);
    }

    @Test
    public void testExpressionSix() throws MismatchedParenthesesException {
        List<String> infixExpr = asList("sin", "(", "1", ")");
        ArrayDeque<String> expectedRPNExpr = new ArrayDeque<>(asList("1", "sin"));

        contains(shuntingYard.toRpn(infixExpr), expectedRPNExpr);
    }

    @Test
    public void testExpressionSeven() throws MismatchedParenthesesException {
        List<String> infixExpr = asList("sin", "(", "cos", "(", "0.75", ")", ")");
        ArrayDeque<String> expectedRPNExpr = new ArrayDeque<>(asList("0.75", "cos", "sin"));

        contains(shuntingYard.toRpn(infixExpr), expectedRPNExpr);
    }

    @Test
    public void testExpressionEight() throws MismatchedParenthesesException {
        List<String> infixExpr = asList("sin", "(", "tan", "(", "2", "+", "3", ")", "/", "3", "x", "3.1415", ")");
        ArrayDeque<String> expectedRPNExpr = new ArrayDeque<>(asList("2", "3", "+", "3", "/", "3.1415", "x", "tan", "sin"));

        contains(shuntingYard.toRpn(infixExpr), expectedRPNExpr);
    }

    @Test
    public void testExpressionNine() throws MismatchedParenthesesException {
        List<String> infixExpr = asList("2", "%");
        ArrayDeque<String> expectedRPNExpr = new ArrayDeque<>(asList("2", "%"));

        contains(shuntingYard.toRpn(infixExpr), expectedRPNExpr);
    }

    @Test(expected = MismatchedParenthesesException.class)
    public void testThrowsMismatchedParenthesesException() throws MismatchedParenthesesException {
        shuntingYard.toRpn(asList("sin", "(", "tan", "(", "2", "+", "3", ")", "/", "3", "x", "3.1415", ")", ")"));
        shuntingYard.toRpn(asList("2", "+", "2", ")"));
        shuntingYard.toRpn(asList("(", "(", "2", "+", "2", ")"));
    }

    private void contains(ArrayDeque<String> actual, ArrayDeque<String> expected) {
        for(String str : expected) {
            assertEquals(str, actual.pollFirst());
        }
    }
}
