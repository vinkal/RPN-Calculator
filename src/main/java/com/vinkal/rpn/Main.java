package com.vinkal.rpn;

import com.vinkal.rpn.exception.CalculatorException;
import com.vinkal.rpn.service.Calculator;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Calculator calculator = new Calculator();
        System.out.println("Enter your expression, or 'q' to quit");
         while (true) {
            if (!console.hasNextLine() ) {
                System.out.println("Exiting program");
                System.exit(0);
            }
            String inputString = console.nextLine();
            if ("q".equals(inputString) ) {
                break;
            } else {
                try {
                    calculator.eval(inputString);
                } catch (CalculatorException e) {
                    System.out.println(e.getMessage());
                }

                DecimalFormat fmt = new DecimalFormat("0.##########");
                Stack<Double> stack = calculator.getValuesStack();
                System.out.print("Stack: ");
                for (Double value : stack) {
                    System.out.print(fmt.format(value));
                    System.out.print(" ");
                }
                System.out.printf("%n");
            }
        }
    }
}
