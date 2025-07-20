//Diego Chiesurin - 2111553

package myExceptions;

/**
 * Thrown to indicate that the requested operation is not supported, extends RuntimeException.
 *
 * Being compliant with the J2SE 1.4.2 constructors
 * using {@code cause} are not required
 */
public class UnsupportedOperationException extends RuntimeException {

    /**
     * Constructs an UnsupportedOperationException with no detail message.
     */
    public UnsupportedOperationException() {
        super("This operation is not supported.");
    }

    /**
     * Constructs an UnsupportedOperationException with the specified
     * detail message.
     *
     * @param message the detail message
     */
    public UnsupportedOperationException(String message) {
        super(message);
    }
}