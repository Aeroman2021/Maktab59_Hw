package hw8.q4.backend.exceptions;

public class DbException extends Exception{
    public DbException(String message,Exception e){
        super(message,e);
    }
}
