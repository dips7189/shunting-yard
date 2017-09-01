package com.dips.shuntingyard.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.sin;
import static java.lang.Math.tan;

public final class Api {

    private static final int leftAssociativity = 1;
    private static final int rightAssociativity = 0;
    private static final Map<String, int[]> operators = new HashMap() {{
        put("^", new int[]{4, 0});
        put("/", new int[]{3, 1});
        put("x", new int[]{3, 1});
        put("+", new int[]{2, 1});
        put("-", new int[]{2, 1});
        put("%", new int[]{5, 1});
    }};

    private enum functions {
        sin, cos, tan, e, log
    }

    /**
     * Checks to see if a given value string is a function in {@link functions}
     *
     * @param token the token to check
     * @return <tt>true</tt> if the token is a function
     */
    public static boolean isFunction(String token) {
        return Arrays.stream(functions.values()).anyMatch(f -> token.equals(f.toString()));
    }

    /**
     * Checks to see if the given token string is an operator in {@link #operators}
     *
     * @param token the token to check
     * @return <tt>true</tt> if the token is an operators
     */
    public static boolean isOperator(String token) {
        return operators.containsKey(token);
    }

    /**
     * Checks to see if the given operator is a unary operator
     *
     * @param operator the operator to check
     * @return whether the operator is unary
     */
    public static boolean isUnaryOperator(String operator) {
        return operator == "%";
    }

    /**
     * Checks to see if the given token string is a real number
     *
     * @param token the token to check
     * @return <tt>true</tt> if the token is a real number
     */
    public static boolean isRealNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks to see if the given token string is a function argument separator
     *
     * @param token the token to check
     * @return <tt>true</tt> if the token is a function argument separator
     */
    public static boolean isFuncArgSeparator(String token) {
        throw new NotImplementedException();
    }

    /**
     * Returns the precedence of the given operator which must exist in {@link #operators}
     *
     * @param operator the operator to find the precedence for in the map of known operators
     * @return the operator precedence
     */
    public static int getPrecedence(String operator) {
        int[] vals = operators.get(operator);
        int precedence = vals[0];

        return precedence;
    }

    /**
     * Returns the associativity of the given operator which must exist in {@link #operators}
     *
     * @param operator the operator to find the associativity for in the map of known operators
     * @return the operator associativity
     */
    public static int getAssociativity(String operator) {
        int[] vals = operators.get(operator);
        int assoc = vals[1];

        return assoc;
    }

    /**
     * Checks to see if the given token string is a left parentheses
     *
     * @param token the token to check
     * @return <tt>true</tt> if the give token is a left parentheses
     */
    public static boolean isLeftParentheses(String token) {
        return token.equals("(");
    }

    /**
     * Checks to see if the give token string is a right parentheses
     *
     * @param token the token to check
     * @return <tt>true</tt> if the given token is a right parentheses
     */
    public static boolean isRightParentheses(String token) {
        return token.equals(")");
    }

    /**
     * Return the integer being used to represent left associativity
     *
     * @return the integer being used to represent left associativity
     */
    public static int getLeftAssociativity() {
        return leftAssociativity;
    }

    /**
     * Return the integer being used to represent right associativity
     *
     * @return the integer being used to represenet right associativity
     */
    public static int getRightAssociativity() {
        return rightAssociativity;
    }

    /**
     * Return the result of applying an operator to two doubles
     *
     * @param operator the operator to apply
     * @param x        a double to apply the operator on
     * @param y        a double to apply the operator on
     * @return the result of applying an operator on two numbers
     */
    public static double applyBinaryOperator(String operator, double x, double y) {
        if (operator.equals("+")) {
            return x + y;

        } else if (operator.equals("-")) {
            return x - y;

        } else if (operator.equals("x")) {
            return x * y;

        } else if (operator.equals("/")) {
            return x / y;

        } else if (operator.equals("^")) {
            return Math.pow(x, y);

        } else {
            return (x * y) / 100;
        }
    }

    /**
     * Evaluates a function against two values
     *
     * @param function the function to evaluate
     * @param x        the value on the left of the function
     * @param y        the value in function
     * @return the result of applying a function with two values
     */
    public static double applyBinaryFunction(String function, double x, double y) {
        if (function.equals("sin")) {
            return x * sin(y);

        } else if (function.equals("cos")) {
            return x * cos(y);

        } else if (function.equals("tan")) {
            return x * tan(y);

        } else if (function.equals("e")) {
            return x * exp(y);

        } else {
            return x * log(y);
        }
    }

    public static double applyUnaryOperator(String operator, double x) {
        return x / 100;
    }

    /**
     * Evaluates a function to one value
     *
     * @param function the function to apply
     * @param x        the value in the function
     * @return the result of applying a function to one value
     */
    public static double applyUnaryFunction(String function, double x) {
        if (function.equals("sin")) {
            return sin(x);

        } else if (function.equals("cos")) {
            return cos(x);

        } else if (function.equals("tan")) {
            return tan(x);

        } else if (function.equals("e")) {
            return exp(x);

        } else {
            return log(x);
        }
    }

}
