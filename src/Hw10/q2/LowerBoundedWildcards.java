package Hw10.q2;

import java.util.Arrays;
import java.util.List;

public class LowerBoundedWildcards {
    public static void main(String[] args) {
        List<Number> numbers = Arrays.asList(1,2,3,4,7,5,6);
        PrintIntegers(numbers);

        List<Number> numbers2 = Arrays.asList(1.2,2.6,3.7,8.0);
        PrintIntegers(numbers2);
    }

    private static void PrintIntegers(List<?super Integer > list){
        System.out.println(list);
    }

}
