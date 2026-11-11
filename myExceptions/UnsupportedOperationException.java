// Diego Chiesurin

package myExceptions;

/**
 * Thrown to indicate that the requested operation is not supported.
 * Extends {@code RuntimeException}.
 * <p>
 * Under CLDC 1.1 / J2SE 1.4.2 constraints, constructors accepting a
 * {@code Throwable cause} parameter are not included.
 */
public class UnsupportedOperationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

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