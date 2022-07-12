package edu.kit.informatik;

import edu.kit.informatik.exceptions.InvalidExpressionException;
import edu.kit.informatik.exceptions.InvalidNumberException;

import java.util.Random;
import java.util.Scanner;

/**
 * The type Input.
 */
public class InputTe {

    private static boolean running;

    private InputTe() { }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        running = true;
        Scanner scanner = new Scanner(System.in);
        Long seed;
        try {
            seed = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException numberFormatException) {
            seed = 1L;
        }
        ExpressionEvaluator.setRandomLongGenerator(new Random(seed));

        while (running) {
            String input = scanner.nextLine();
            if (input.equals("quit")) {
                running = false;
                break;
            } else {
                try {
                    ComplexNumber result = ExpressionEvaluator.evaluate(input);
                    System.out.println(result.toString());
                } catch (InvalidNumberException | InvalidExpressionException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }
        scanner.close();
    }

    /**
     * Main test complex number.
     *
     * @param testInputs the test inputs
     * @return the complex number
     */
    public static ComplexNumber mainTest(String testInputs) {
        running = true;
        Scanner scanner = new Scanner(testInputs);
        Long seed;
        try {
            seed = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException numberFormatException) {
            seed = 1L;
        }
        ExpressionEvaluator.setRandomLongGenerator(new Random(seed));
        while (running) {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (input.equals("quit")) {
                    running = false;
                    break;
                } else {
                    try {
                        ComplexNumber result = ExpressionEvaluator.evaluate(input);

                        System.out.println(input + " = " + result.toString());
                    } catch (InvalidNumberException | InvalidExpressionException exception) {
                        System.out.println(exception.getMessage());
                    }
                }
            } else {
                running = false;
                break;
            }
        }
        scanner.close();
        return null;
    }
}
