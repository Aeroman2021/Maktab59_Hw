package Hw10.q1.utility;

public class Constant {
    private static final String SPACES = "%-15s";
    private static final String ID_SPACES = "%-2s";
    private static final String NAME_SPACES = "%-4s";
    private static final String FORM_SPACES = "%-4s";
    private static final String PRICE_SPACES = "%-4s";
    private static final String QUANTITY_SPACES = "%-4s";
    private static final String IS_EXIST = "%-8s";

    public static final String COLUMN_SEPARATOR = " | ";
    public static String formatter(String str){
        return String.format(SPACES,str);
    }
    public static String idFormatter(String str){
        return String.format(ID_SPACES,str);
    }
    public static String nameFormatter(String str){
        return String.format(NAME_SPACES,str);
    }
    public static String formFormatter(String str){
        return String.format(FORM_SPACES,str);
    }
    public static String priceFormatter(String str){
        return String.format(PRICE_SPACES,str);
    }
    public static String quantityFormatter(String str){
        return String.format(QUANTITY_SPACES,str);
    }
    public static String isExistFormatter(String str){
        return String.format(IS_EXIST,str);
    }

}
