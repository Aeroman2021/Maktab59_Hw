package Hw10.q1.utility;

public class Constant {
    private static final String SPACES = "%-15s";
    public static final String COLUMN_SEPARATOR = " | ";
    public static String formatter(String str){
        return String.format(SPACES,str);
    }
}
