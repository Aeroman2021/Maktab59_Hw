package jdbcExample.exception;

public class ItemIsExist extends RuntimeException{
    public ItemIsExist() {
    }

    public ItemIsExist(String message) {
        super(message);
    }

    public ItemIsExist(String message, Throwable cause) {
        super(message, cause);
    }
}
