package Hw10.q1.backend.Exception;

public class ServiceException extends  Exception{
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
