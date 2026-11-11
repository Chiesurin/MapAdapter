// Diego Chiesurin

package myExceptions;

/**
 * Signals that a method has been invoked at an illegal or
 * inappropriate time. In other words, the Java environment or
 * Java application is not in an appropriate state for the requested
 * operation.
 * <p>
 * Under CLDC 1.1 / J2SE 1.4.2 constraints, constructors accepting a
 * {@code Throwable cause} parameter are not included.
 */
public class IllegalStateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an IllegalStateException with no detail message.
     * A detail message is a String that describes this particular exception.
     */
    public IllegalStateException() {
        super("Illegal state.");
    }

    /**
     * Constructs an IllegalStateException with the specified detail
     * message. A detail message is a String that describes this particular
     * exception.
     *
     * @param message the String that contains a detailed message
     */
    public IllegalStateException(String message) {
        super(message);
    }
}