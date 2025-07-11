package myExceptions;

//specificare che essendo il CLDC1.1 non si supportano i Throwable, indagare
public class UnsupportedOperationException extends RuntimeException {

    public UnsupportedOperationException() {
        super("This operation is not supported.");
    }

    public UnsupportedOperationException(String message) {
        super(message);
    }
}