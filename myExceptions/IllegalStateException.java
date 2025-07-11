package myExceptions;

//specificare che essendo il CLDC1.1 non si supportano i Throwable, indagare
public class IllegalStateException extends RuntimeException {

    public IllegalStateException() {
        super("Illegal state.");
    }

    public IllegalStateException(String message) {
        super(message);
    }
}