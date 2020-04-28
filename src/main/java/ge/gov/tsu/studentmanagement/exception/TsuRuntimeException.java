package ge.gov.tsu.studentmanagement.exception;

public class TsuRuntimeException extends RuntimeException {

    public TsuRuntimeException() {
        super();
    }

    public TsuRuntimeException(String message) {
        super(message);
    }
}
