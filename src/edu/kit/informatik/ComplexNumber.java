package edu.kit.informatik;

import edu.kit.informatik.exceptions.InvalidExpressionException;
import edu.kit.informatik.exceptions.InvalidNumberException;

/**
 * The Complex number class
 *
 * @author ugqws
 * @version 1.2
 */
public class ComplexNumber {

    private static final String KOMPLEX_NUMBER_FORMAT = "\\((-?\\d*|R)\\+(-?\\d*|R)i\\)$";
    private Long realPart;
    private Long imaginaryPart;


    /**
     * Instantiates a new Complex number.
     *
     * @param realPart      the real part
     * @param imaginaryPart the imaginary part
     */
    public ComplexNumber(Long realPart, Long imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    /**
     * Parse complex number.
     *
     * @param toParse the complex number represented as a string
     * @return the complex number
     * @throws InvalidNumberException the invalid number exception if
     */
    public static ComplexNumber parseComplexNumber(String toParse) throws InvalidNumberException {
        if (toParse.matches(KOMPLEX_NUMBER_FORMAT)) {
            int middleIndex = toParse.indexOf('+');
            String realPart = toParse.substring(1, middleIndex);
            String imagPart = toParse.substring(middleIndex + 1, toParse.indexOf(')') - 1);
            try {
                Long real = realPart.equals("R") ? ExpressionEvaluator.getRandomLong() : Long.parseLong(realPart);
                Long imaginary = imagPart.equals("R") ? ExpressionEvaluator.getRandomLong() : Long.parseLong(imagPart);

                return new ComplexNumber(real, imaginary);

            } catch (NumberFormatException numberFormatException) {
                throw new InvalidNumberException("Error, number out of Long range.");
            }
        }
        throw new InvalidNumberException("Error, complex number " + toParse + " does not match the format.");
    }

    //region operator calculations
    /**
     * Add complex numbers.
     *
     * @param z1 the first number
     * @param z2 the second number
     * @return the result of the addition
     */
    public static ComplexNumber add(ComplexNumber z1, ComplexNumber z2) {
        return new ComplexNumber(z1.realPart + z2.realPart, z1.imaginaryPart + z2.imaginaryPart);
    }

    /**
     * Subtract complex numbers.
     *
     * @param z1 the first number
     * @param z2 the second number
     * @return the result of the subtraction
     */
    public static ComplexNumber subtract(ComplexNumber z1, ComplexNumber z2) {
        return new ComplexNumber(z1.realPart - z2.realPart, z1.imaginaryPart - z2.imaginaryPart);
    }

    /**
     * Multiply complex numbers.
     *
     * @param z1 the first number
     * @param z2 the second number
     * @return the result of the multiplication
     */
    public static ComplexNumber multiply(ComplexNumber z1, ComplexNumber z2) {
        Long a = z1.realPart;
        Long b = z1.imaginaryPart;
        Long c = z2.realPart;
        Long d = z2.imaginaryPart;

        Long tempRealPart = (a * c) - (b * d);
        Long tempImaginaryPart = (a * d) + (b * c);
        return new ComplexNumber(tempRealPart, tempImaginaryPart);
    }

    /**
     * Divide complex numbers.
     *
     * @param z1 the first number
     * @param z2 the second number
     * @return the result of the division
     * @throws InvalidExpressionException the invalid expression exception if the divisor is zero
     */
    public static ComplexNumber divide(ComplexNumber z1, ComplexNumber z2) throws InvalidExpressionException {
        Long a = z1.realPart;
        Long b = z1.imaginaryPart;
        Long c = z2.realPart;
        Long d = z2.imaginaryPart;

        Long divisor = (c * c) + (d * d);
        if (divisor == 0L) {
            throw new InvalidExpressionException("Error, division by zero.");
        }
        Long tempRealPart = ((a * c) + (b * d)) / divisor;
        Long tempImaginaryPart = ((b * c) - (a * d)) / divisor;
        return new ComplexNumber(tempRealPart, tempImaginaryPart);
    }
    //endregion

    /**
     * returns this number as a string in the given format
     *
     * @return the complex number represented as a string
     */
    public String toString() {
        return "(" + realPart + "+" + imaginaryPart + "i)";
    }




}
