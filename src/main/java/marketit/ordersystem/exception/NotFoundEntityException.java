package marketit.ordersystem.exception;

public class NotFoundEntityException extends RuntimeException {

    public NotFoundEntityException() {
    }

    public NotFoundEntityException(String message) {
        super(message);
    }
}
