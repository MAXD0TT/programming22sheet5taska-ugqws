package edu.kit.informatik.exceptions;

/**
 * Invalid number exception.
 *
 * @author ugqws
 * @version 1.0
 */
public class InvalidNumberException extends Exception {

    /**
     * Instantiates a new invalid number exception.
     *
     * @param errorMessage the error message for this exception
     */
    public InvalidNumberException(String errorMessage) {
        super(errorMessage);
    }
}
