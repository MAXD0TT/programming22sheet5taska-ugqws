package edu.kit.informatik;

import edu.kit.informatik.exceptions.InvalidExpressionException;
import edu.kit.informatik.exceptions.InvalidNumberException;

import java.util.Random;
import java.util.Scanner;

/**
 * The class Input for handling the In and Output of the expression evaluator.
 *
 * @author ugqws
 * @version 1.2
 */
public class Input {

    private static boolean running;

    private Input() { }

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
            seed = Long.parseLong(args[0]);
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
}
