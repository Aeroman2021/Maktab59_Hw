package Hw10.q2;

import java.util.List;

public class UpperBoundedWildcards {


    public static void main(String[] args) {

        List<Integer> integerList = List.of(1,2,3,4,5);
        System.out.println(Sum(integerList));

        List<Double> doubleList = List.of(1.5,2.75,6.9,9.3,1.0);
        System.out.println(Math.round(Sum(doubleList)));

    }

    private static double Sum(List<? extends Number> list){
        double sum=0;
        for (Number number : list){
            sum += number.doubleValue();
        }

        return sum;
    }
}
