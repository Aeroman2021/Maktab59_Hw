package Hw10.q1.utility;

import java.util.Scanner;

public class Input {

    private static Scanner input = new Scanner(System.in);

    public static String getOptionalStringInputValue(String msg) {
        System.out.print(msg + " ");
        return input.nextLine().trim();
    }

    public static String getStringInputValue(String msg) {
        String result = null;
        while (true) {
            result = getOptionalStringInputValue(msg);
            if (result.isEmpty())
                Printer.printErrorMessage("Invalid input!");
            else
                return result;
        }
    }

    public static int getInputValue(String msg) {
        while (true)
            try {
                return Integer.parseInt(getStringInputValue(msg));
            } catch (Exception e) {
                Printer.printErrorMessage("Invalid input");
                if (msg.isEmpty())
                    System.out.println("> ");

            }
    }


}
