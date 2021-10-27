package Hw10.q2;

import java.util.Arrays;
import java.util.List;

public class UnboundedWildcards {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1,2,3,4,5,6);
        List<Double> list2 = Arrays.asList(2.0,3.4,5.6,1.0);
        printList(list1);
        printList(list2);

    }

    private static void printList(List<?> list){
        System.out.println(list);
    }
}
