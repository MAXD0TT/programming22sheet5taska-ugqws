package edu.kit.informatik;

import edu.kit.informatik.exceptions.InvalidNumberException;

import static org.junit.jupiter.api.Assertions.*;

class ComplexNumberTest {

    @org.junit.jupiter.api.Test
    void parseKomplexNumber() {
        String test1 = "(1234+4321i)";
        try {
            String return1 = ComplexNumber.parseComplexNumber(test1).toString();
            assertEquals(test1, return1);
        } catch (InvalidNumberException e) {
            System.out.println(e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void add() {
    }

    @org.junit.jupiter.api.Test
    void subtract() {
    }

    @org.junit.jupiter.api.Test
    void multiply() {
    }

    @org.junit.jupiter.api.Test
    void divide() {
    }
}