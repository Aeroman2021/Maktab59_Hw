package hw8.q2;

public class ExceptionB extends ExceptionA {

    public ExceptionB() {
        super("Invalid Input.You have entered too large number");
    }

    public ExceptionB(String message) {
        super(message);
    }
}
