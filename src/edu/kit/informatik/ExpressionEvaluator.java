package edu.kit.informatik;

import edu.kit.informatik.exceptions.InvalidExpressionException;
import edu.kit.informatik.exceptions.InvalidNumberException;

import java.util.Random;
import java.util.Stack;

public class ExpressionEvaluator {

    private static Random randomLongGenerator;

    private static final String OPERATOR_SYMBOLS = "+-*/";

    private static Stack<ComplexNumber> numbers;
    private static Stack<Character> operators;

    public static ComplexNumber evaluate(String rawExpression)
            throws InvalidNumberException, InvalidExpressionException {

        char[] tokens = rawExpression.toCharArray();
        numbers = new Stack<>();
        operators = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {


            if (tokens[i] == '(') {
                boolean foundClosingBracket = false;
                for (int j = i; j < tokens.length; j++) {
                    if (tokens[j] == ')') {
                        numbers.push(ComplexNumber.parseComplexNumber(rawExpression.substring(i, j + 1)));
                        i = j;
                        foundClosingBracket = true;
                        break;
                    }
                }
                if (!foundClosingBracket) {
                    throw new InvalidNumberException("Error, no closing brackets found for complex number");
                }
            } else if (tokens[i] == '[') {
                operators.push(tokens[i]);
            } else if (tokens[i] == ']') {
                while (operators.peek() != '[') {
                    calculateNext();
                }
                operators.pop();
            } else if (OPERATOR_SYMBOLS.contains(String.valueOf(tokens[i]))) {
                while (!operators.empty() && Operator.precedence(tokens[i], operators.peek())) {
                    calculateNext();
                }
                operators.push(tokens[i]);
            } else {
                throw new InvalidExpressionException("Error, unrecognized symbol in expression on index: " + i + ".");
            }
        }

        calculateRemaining();

        return numbers.pop();
    }

    private static void calculateNext() throws InvalidExpressionException {
        if (operators.peek() == '[') {
            throw new InvalidExpressionException("Error, no closing square bracket.");
        }

        Operator operator = Operator.parseOperator(operators.pop());
        ComplexNumber z2 = numbers.pop();
        ComplexNumber z1 = numbers.pop();
        numbers.push(operator.evaluate(z1, z2));
    }

    private static void calculateRemaining() throws InvalidExpressionException {
        while (!operators.empty()) {
            calculateNext();
        }
    }

    public static void setRandomLongGenerator(Random randomLongGenerator) {
        ExpressionEvaluator.randomLongGenerator = randomLongGenerator;
    }

    public static Long getRandomLong() {
        return randomLongGenerator.nextLong();
    }


}
