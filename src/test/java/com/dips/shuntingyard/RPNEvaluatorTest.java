package com.dips.shuntingyard;

import java.util.ArrayDeque;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class RPNEvaluatorTest {

    private RPNEvaluator evaluator = new RPNEvaluator();

    @Test
    public void evaluateExpressionOne() {
        Double result = evaluator.evaluate(new ArrayDeque<>(asList("1", "2", "+")));
        assertEquals(3, result, 0);
    }

    @Test
    public void evaluateExpressionTwo() {
        Double result = evaluator.evaluate(new ArrayDeque<>(asList("5000", "33", "4", "+", "x")));
        assertEquals(185000, result, 0);
    }

    @Test
    public void evaluateExpressionThree() {
        Double result = evaluator.evaluate(new ArrayDeque<>(asList("5000", "8", "4", "+", "sin", "x")));
        assertEquals(-2682.8645900021747, result, 0);
    }

    @Test
    public void evaluateExpressionFour() {
        Double result = evaluator.evaluate(new ArrayDeque<>(asList("0.75", "cos", "sin")));
        assertEquals(0.6681271852105274, result, 0);
    }

    @Test
    public void evaluateExpressionFive() {
        Double result = evaluator.evaluate(new ArrayDeque<>(asList("2", "%")));
        assertEquals(0.02, result, 0);
    }
}
