package hw14.q3;

import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.stream().filter(var->var>1).forEach(System.out::println);

    }
}
