package edu.kit.informatik;

import edu.kit.informatik.exceptions.InvalidExpressionException;
import edu.kit.informatik.exceptions.InvalidNumberException;

import java.util.Random;
import java.util.Stack;

/**
 * The type Expression evaluator.
 */
public class ExpressionEvaluator {

    private static Random randomLongGenerator;

    private static final String OPERATOR_SYMBOLS = "+-*/";

    private static Stack<ComplexNumber> numbers;
    private static Stack<Character> operators;

    private ExpressionEvaluator() { }

    /**
     * Evaluate complex number.
     *
     * @param rawExpression the raw expression
     * @return the complex number
     * @throws InvalidNumberException     the invalid number exception
     * @throws InvalidExpressionException the invalid expression exception
     */
    public static ComplexNumber evaluate(String rawExpression)
            throws InvalidNumberException, InvalidExpressionException {

        char[] tokens = rawExpression.toCharArray();
        numbers = new Stack<>();
        operators = new Stack<>();

        //add every token to the corresponding stack
        for (int i = 0; i < tokens.length; i++) {

            //try to parse string between the round brackets
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
            }
            //add [ to operator stack
            else if (tokens[i] == '[') {
                operators.push(tokens[i]);
            }
            //evaluate the expression in the brackets and add it to the numbers stack
            else if (tokens[i] == ']') {
                while (operators.peek() != '[') {
                    calculateNext();
                }
                operators.pop();
            }
            // add operator and calculate
            else if (OPERATOR_SYMBOLS.contains(String.valueOf(tokens[i]))) {
                while (!operators.empty() && Operator.precedence(tokens[i], operators.peek())) {
                    calculateNext();
                }
                operators.push(tokens[i]);
            }
            // illegal symbol in expression
            else {
                throw new InvalidExpressionException("Error, unrecognized symbol in expression on index: " + i + ".");
            }
        }
        //evaluate all remaining operators
        calculateRemaining();
        //return the last remaining number, which is the solution.
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

    /**
     * Sets random long generator.
     *
     * @param randomLongGenerator the random long generator
     */
    public static void setRandomLongGenerator(Random randomLongGenerator) {
        ExpressionEvaluator.randomLongGenerator = randomLongGenerator;
    }

    /**
     * Get the next random long.
     *
     * @return the random long
     */
    public static Long getRandomLong() {
        return randomLongGenerator.nextLong();
    }


}
