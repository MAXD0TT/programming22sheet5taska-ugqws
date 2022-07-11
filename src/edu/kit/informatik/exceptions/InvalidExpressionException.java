package edu.kit.informatik.exceptions;

/**
 * Invalid expression exception.
 *
 * @author ugqws
 * @version 1.0
 */
public class InvalidExpressionException extends Exception {

    /**
     * Instantiates a new invalid expression exception.
     *
     * @param errorMessage the error message for this exception
     */
    public InvalidExpressionException(String errorMessage) {
        super(errorMessage);
    }
}