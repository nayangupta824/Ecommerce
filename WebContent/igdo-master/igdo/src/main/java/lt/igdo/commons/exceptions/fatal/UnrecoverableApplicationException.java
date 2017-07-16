/**
 * UnrecoverableApplicationException.java
 * Created: Feb 13, 2008 7:54:59 PM
 */
package lt.igdo.commons.exceptions.fatal;


/**
 * Exception for non-recoverable failures.
 * 
 * @author Donatas
 * 
 */
public class UnrecoverableApplicationException extends RuntimeException {

    /** Generated serialVersionUID. */
    private static final long serialVersionUID = -4418077271329576684L;

    /**
     * Constructor.
     */
    public UnrecoverableApplicationException() {
    }

    /**
     * Constructor with parameters.
     * 
     * @param message
     *            exception message.
     * @param cause
     *            exception cause.
     */
    public UnrecoverableApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with parameters.
     * 
     * @param message
     *            exception message.
     */
    public UnrecoverableApplicationException(String message) {
        super(message);
    }

    /**
     * Constructor with parameters.
     * 
     * @param cause
     *            exception cause.
     */
    public UnrecoverableApplicationException(Throwable cause) {
        super(cause);
    }

}
