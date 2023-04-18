package exceptions;

public class CustomException extends RuntimeException {
    private String code;

    public CustomException(String message) {
        super(message);
    }
}
