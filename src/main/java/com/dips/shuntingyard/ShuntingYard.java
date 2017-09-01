package com.dips.shuntingyard;

import com.dips.shuntingyard.exceptions.MismatchedParenthesesException;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import static com.dips.shuntingyard.api.Api.getAssociativity;
import static com.dips.shuntingyard.api.Api.getLeftAssociativity;
import static com.dips.shuntingyard.api.Api.getPrecedence;
import static com.dips.shuntingyard.api.Api.isFunction;
import static com.dips.shuntingyard.api.Api.isLeftParentheses;
import static com.dips.shuntingyard.api.Api.isOperator;
import static com.dips.shuntingyard.api.Api.isRealNumber;
import static com.dips.shuntingyard.api.Api.isRightParentheses;

public class ShuntingYard {

    private Stack<String> functionStack = new Stack<>();
    private ArrayDeque<String> outputQueue = new ArrayDeque<>();

    private static final String MISMATCHED_PARENTHESES_MSG = "There are mismatched parentheses";

    /**
     * Take an infix expression such as: 3 + (cos(0.75) * 5 - 2) / 3 and return the postfix (reverse polish
     * notation) equivalent
     *
     * @param tokens list of tokens from the infix expression
     * @return list of tokens from the postfix expression
     */
    public ArrayDeque<String> toRpn(List<String> tokens) throws MismatchedParenthesesException {

        for (String token : tokens) {

            //if the token is a number
            if (isRealNumber(token)) outputQueue.add(token);

            //if the token is a function
            if (isFunction(token)) functionStack.push(token);

            //if the token is a function argument separator --LEFT OUT

            //if the token is an operator
            if (isOperator(token)) {
                Iterator<String> iter = functionStack.iterator();
                while (iter.hasNext()) {

                    if (isLeftParentheses(functionStack.peek())) {
                        functionStack.push(token);
                        break;
                    } else if (isFunction(functionStack.peek())) {
                        functionStack.push(token);
                        break;
                    } else if (isOperator(functionStack.peek()) &&
                        (getAssociativity(token) == getLeftAssociativity() && getPrecedence(token) <= getPrecedence(functionStack.peek())) ||
                        getPrecedence(token) < getPrecedence(functionStack.peek())) {

                        //pop the operator from the function stack and add it to the output queue
                        outputQueue.add(functionStack.pop());
                        functionStack.push(token);
                        break;
                    } else {
                        functionStack.push(token);
                        break;
                    }
                }

                if (functionStack.size() == 0) functionStack.push(token);

            }

            //if the token is a left parentheses
            if (isLeftParentheses(token)) functionStack.push(token);

            //if the token is a right parentheses
            if (isRightParentheses(token)) {
                Iterator<String> iter = functionStack.iterator();
                int numLeftParenthesesFunctions = 0;
                while (iter.hasNext()) {
                    //operators off the stack on to the output queue
                    if (isOperator(functionStack.peek())) {
                        outputQueue.add(functionStack.pop());
                        //functions off the stack on to the output queue
                    } else if (isFunction(functionStack.peek())) {
                        outputQueue.add(functionStack.pop());
                        //left parentheses off the stack
                    } else if (isLeftParentheses(functionStack.peek())) {
                        numLeftParenthesesFunctions++;
                        functionStack.pop();
                        break;
                    }
                }
                //if no left parentheses were found then there are mismatched parentheses
                if (numLeftParenthesesFunctions == 0) {
                    throw new MismatchedParenthesesException(MISMATCHED_PARENTHESES_MSG);
                }
            }
        }

        //when there are no more tokens to be read i.e. when we exit the loop
        while (!functionStack.isEmpty()) {

            //if there is a parentheses or a function then there is a parentheses/function mismatch
            if (isLeftParentheses(functionStack.peek()) || isRightParentheses(functionStack.peek())) {
                throw new MismatchedParenthesesException(MISMATCHED_PARENTHESES_MSG);
            } else if (isOperator(functionStack.peek()) || isFunction(functionStack.peek())) {
                outputQueue.add(functionStack.pop());
            }

        }

        return outputQueue;
    }

}