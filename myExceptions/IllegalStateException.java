//Diego Chiesurin - 2111553

package myExceptions;

/**
 * Signals that a method has been invoked at an illegal or
 * inappropriate time. In other words, the Java environment or
 * Java application is not in an appropriate state for the requested
 * operation.
 * 
 * Being compliant with J2SE 1.4.2 the constructors
 * using {@code cause} are not required
 */
public class IllegalStateException extends RuntimeException {

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