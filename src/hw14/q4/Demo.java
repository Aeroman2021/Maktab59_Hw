package hw14.q4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<String> nameList = List.of("Amir", "Hatef", "Mehran", "Mojtaba", "Mohammad",
                "Ali", "Davood", "Reza", "Mohsen");
        nameList.stream().sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);
        System.out.println("=========");
        nameList.stream().sorted(Comparator.comparingInt(String::length))
                .forEach(a-> System.out.println(a + " -> " + a.length()));
        System.out.println("=========");
        Map<Integer,List<String>> nameChecker = nameList.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(nameChecker);




    }
}
