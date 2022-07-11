package edu.kit.informatik;

import edu.kit.informatik.exceptions.InvalidExpressionException;

/**
 * The enum Operator.
 *
 * @author ugqws
 * @version 1.2
 */
public enum Operator {
    /**
     * Add operator.
     */
    ADD('+'),
    /**
     * Subtract operator.
     */
    SUBTRACT('-'),
    /**
     * Multiply operator.
     */
    MULTIPLY('*'),
    /**
     * Divide operator.
     */
    DIVIDE('/');

    private char identifier;

    Operator(char identifier) {
        this.identifier = identifier;
    }

    /**
     * Parse operator from char
     *
     * @param toParse the char to parse
     * @return the matching operator
     */
    public static Operator parseOperator(char toParse) {
        for (Operator operator : Operator.values()) {
            if (operator.identifier == toParse) {
                return operator;
            }
        }
        return null; // testing for valid operators already done.
    }

    /**
     * Evaluate complex number.
     *
     * @param z1 the first complex number
     * @param z2 the second complex number
     * @return the result of the calculation
     * @throws InvalidExpressionException the invalid expression exception if z2 is zero for the division
     */
    public ComplexNumber evaluate(ComplexNumber z1, ComplexNumber z2) throws InvalidExpressionException {
        switch (this) {
            case ADD:
                return ComplexNumber.add(z1, z2);
            case SUBTRACT:
                return ComplexNumber.subtract(z1, z2);
            case MULTIPLY:
                return ComplexNumber.multiply(z1, z2);
            case DIVIDE:
                return ComplexNumber.divide(z1, z2);
        }
        return null; // testing for valid operators already done.
    }

    /**
     * Precedence boolean.
     *
     * @param operator1 the operator 1
     * @param operator2 the operator 2
     * @return the boolean
     */
    public static boolean precedence(char operator1, char operator2) {
        return !(((operator1 == '*' || operator1 == '/')
                && (operator2 == '+' || operator2 == '-'))
                || operator2 == '[' || operator2 == ']');


    }
}
