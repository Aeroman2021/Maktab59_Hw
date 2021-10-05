package Hw9.q3;


import java.util.Scanner;

public class Runner {
    static Scanner input = new Scanner(System.in);
    static LinkedList ls = new LinkedList();

    public static void main(String[] args) throws NoSuchFieldException {
        UI();
    }

    public static void showMenu() {
        System.out.println();
        System.out.println("=================================================");
        System.out.println("1) Insert a value at the beginning of LinkedList");
        System.out.println("2) Insert a value at the end of LinkedList");
        System.out.println("3) remove a value at the beginning of LinkedList");
        System.out.println("4) remove a value at the end of LinkedList");
        System.out.println("5) Find the index of an item in the LinkedList");
        System.out.println("6) print whether the LinkedList is Empty or not");
        System.out.println("7) print whether the element exists in the LinkedList.");
        System.out.println("8) print all the elements in the LinkedList.");
        System.out.println("9) Exit");
        System.out.println();
        System.out.println("Please select a number");
    }

    public static void UI() throws NoSuchFieldException {


        while (true) {

            showMenu();
            int choice = input.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("how many number you want to insert at the beginning of the list");
                    int number = input.nextInt();
                    for (int i = 0; i < number; i++) {
                        System.out.println("Enter a value");
                        int value = input.nextInt();
                        ls.addFirst(ls,value);
                    }
                    ls.printLinkedList(ls);
                    System.out.println();
                }

                case 2 -> {
                    System.out.println("how many number you want to insert at the end of the list");
                    int number = input.nextInt();
                    for (int i = 0; i < number; i++) {
                        System.out.println("Enter a value");
                        int value = input.nextInt();
                        ls.addLast(ls,value);
                    }
                    ls.printLinkedList(ls);
                    System.out.println();
                }


                case 3 -> {
                    System.out.println("how many number you want to delete at the beginning of the list");
                    int number = input.nextInt();
                    for (int i = 0; i < number; i++) {
                        ls.removeFirst(ls);
                    }
                    ls.printLinkedList(ls);
                    System.out.println();
                }


                case 4 -> {
                    System.out.println("how many number you want to delete at the end of the list");
                    int number = input.nextInt();
                    for (int i = 0; i < number; i++) {
                        ls.removeLast(ls);
                    }
                    ls.printLinkedList(ls);
                    System.out.println();
                }

                case 5 -> {
                    System.out.println("Enter the number");
                    int item = input.nextInt();
                    System.out.println(ls.indexOf(ls,item));
                }

                case 6 -> {
                    if (ls.isEmpty(ls))
                        System.out.println("The list is empty.");
                    else
                        System.out.println("The list is not empty");
                }

                case 7 -> {
                    System.out.println("Print the value of the Item");
                    int item = input.nextInt();
                    if (ls.contain(ls,item))
                        System.out.println("Thee input item exists in the list.");
                    else
                        System.out.println("The input item does not exist in the list");
                }

                case 8 -> {
                    System.out.println("The item in the list are: ");
                    ls.printLinkedList(ls);
                }

                case 9 -> {
                    System.out.println("Exiting the application...");
                    System.exit(0);
                }

                default -> System.out.println("Invalid input! please select a number between 1 to 9.");
            }

        }
    }


}
