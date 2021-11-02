package jdbcExample.utility;


import java.util.ArrayList;

public class IdGenerator {

    private static ArrayList<Integer> idStore = new ArrayList<>();


    public static int idGenerator() {
        int upperBound = 999;
        int lowerBound = 100;
        int id = (int) (Math.random() * (upperBound - lowerBound)) + lowerBound;
        if (!idStore.contains(id)) {
            idStore.add(id);
            return id;
        } else idGenerator();
        return id;
    }
}
