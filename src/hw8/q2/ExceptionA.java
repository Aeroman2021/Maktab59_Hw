package hw8.q2;

public class ExceptionA extends Exception{
    public ExceptionA(){
        super("Invalid input.You have entered out of range number!");
    }

    public ExceptionA(String message) {
        super(message);
    }
}
