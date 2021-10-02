package hw8.q3;

import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);


    public static void main(String[] args) {
        someMethod();


    }

    private static void someMethod() {
        try {
            someMethod2();
        } catch (Exception e2) {
            e2.getStackTrace();
            throw new ArithmeticException();
        }
    }

    private static void someMethod2() {
        System.out.println("Enter the first number:");
        int a = input.nextInt();
        System.out.println("Enter the second number:");
        int b = input.nextInt();

        try {
            int c = a / b;
            System.out.println("The result of a/b = " + c);
        } catch (ArithmeticException e1) {
            e1.getStackTrace();
            System.out.println(e1.getMessage());
        }

    }
}
