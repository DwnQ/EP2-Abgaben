// Represents a checked exception thrown by a 'SafeFactory' object.
//
public class FactoryException extends Exception {
    private String errorMessage;

    public FactoryException() {
        super();
    }

    public FactoryException(String message) {
        super(message);
        errorMessage = message;
    }

    public FactoryException(String message, Throwable cause) {
        super(message, cause);
        errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
