package hw14.q2;

import hw11.q4.Producer;

import java.util.function.*;

public class StringChecker {
    public static void main(String[] args) {


        Predicate<String> firstAndLastCharIsEqual = a-> String.valueOf(a.charAt(0)).
                equalsIgnoreCase(String.valueOf(a.charAt(a.length() - 1)));
        boolean test1 = firstAndLastCharIsEqual.test("Ali");
        boolean test2 = firstAndLastCharIsEqual.test("AVA");
        System.out.println(test1);
        System.out.println(test2);


    }
}
