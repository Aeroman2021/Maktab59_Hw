package Hw9.q2;
import java.util.ArrayList;

public class Runner {

    static ArrayList<Integer> inputArray = new ArrayList<Integer>();

    public static void main(String[] args) {

        inputArray.add(3);
        inputArray.add(7);
        inputArray.add(9);
        inputArray.add(2);
        inputArray.add(5);
        inputArray.add(5);
        inputArray.add(8);
        inputArray.add(5);
        inputArray.add(6);
        inputArray.add(3);
        inputArray.add(4);
        inputArray.add(7);
        inputArray.add(3);
        inputArray.add(1);

        reArranger(inputArray);
    }

    public static void reArranger(ArrayList<Integer> numbers) {

        if (numbers.isEmpty()) {
            System.out.println(numbers);
        } else if (numbers.size() % 2 == 0) {
            removeUndesiredElement(numbers);
            System.out.println(numbers);
        } else {
            numbers.remove(numbers.size() - 1);
            removeUndesiredElement(numbers);
            System.out.println(numbers);
        }
    }

    private static void removeUndesiredElement(ArrayList<Integer> numbers) {
        for (int i = numbers.size()-1; i >= 0; i += -2) {
            if (numbers.get(i) < numbers.get(i - 1)) {
                numbers.remove(i);
                numbers.remove(i-1);
            }
        }
    }
}
