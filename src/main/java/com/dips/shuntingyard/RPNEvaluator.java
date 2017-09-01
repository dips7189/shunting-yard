package com.dips.shuntingyard;

import java.util.Queue;
import java.util.Stack;

import static com.dips.shuntingyard.api.Api.applyBinaryFunction;
import static com.dips.shuntingyard.api.Api.applyBinaryOperator;
import static com.dips.shuntingyard.api.Api.applyUnaryFunction;
import static com.dips.shuntingyard.api.Api.applyUnaryOperator;
import static com.dips.shuntingyard.api.Api.isFunction;
import static com.dips.shuntingyard.api.Api.isOperator;
import static com.dips.shuntingyard.api.Api.isRealNumber;
import static com.dips.shuntingyard.api.Api.isUnaryOperator;
import static java.lang.String.valueOf;

public class RPNEvaluator {

    private Stack<String> stack = new Stack<>();

    public Double evaluate(Queue<String> rpnExpr) {
        for (String token : rpnExpr) {
            // check real number
            if (isRealNumber(token)) {
                stack.push(token);
                //check operator and function
            } else if (isOperator(token) || isFunction(token)) {
                //check whether to use unary/binary function/operator
                if (stack.size() == 1) {
                    if (isUnaryOperator(token)) {
                        stack.push(valueOf(applyUnaryOperator(token, Double.parseDouble(stack.pop()))));
                    } else if (isFunction(token)) {
                        stack.push(valueOf(applyUnaryFunction(token, Double.parseDouble(stack.pop()))));
                    }
                } else {
                    double y = Double.parseDouble(stack.pop());
                    double x = Double.parseDouble(stack.pop());

                    if (isOperator(token)) {
                        stack.push(valueOf(applyBinaryOperator(token, x, y)));
                    } else if (isFunction(token)) {
                        stack.push(valueOf(applyBinaryFunction(token, x, y)));
                    }
                }
            }
        }

        return Double.parseDouble(stack.pop());
    }
}
