package hw8.q1;


import hw8.q1.entities.*;
import java.util.Scanner;

public class DemoTurner {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true){
            Menu();
            int option = scanner.nextInt();
            switch (option){
                case 1:
                    Leaf leaf = new Leaf();

                    break;

                case 2:
                    Page page = new Page();
                    break;

                case 3:
                    Pancake pancake = new Pancake();
                    break;

                case 4:
                    Lights lights = new Lights();
                    break;

                case 5:
                    Angle angle = new Angle();
                    break;

                case 6:
                    System.out.println("Exiting the application...");
                    System.exit(0);
                    break;


                default:
                    System.out.println("Please enter the correct number");

            }
        }
    }

    private static void Menu(){
        System.out.println("This code shows the application of Turner Interface");
        System.out.println();
        System.out.println("1) Change the color of Leaf");
        System.out.println("2) go to the next page");
        System.out.println("3) showing the next side of pancake");
        System.out.println("4) turn on or of the light");
        System.out.println("5) Change the angle");
        System.out.println("6) Exit the program");
        System.out.println("Please select a number");


    }

}
