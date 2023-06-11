// Represents an exception thrown by a 'SafeOperation' object.
//
public class OperationException extends Exception {
    private String errorMessage;
    public OperationException() {
        super();
    }
    public OperationException(String message) {
        super(message);
        errorMessage = message;
    }
    public OperationException(String message, Throwable cause) {
        super(message, cause);
        errorMessage = message;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
