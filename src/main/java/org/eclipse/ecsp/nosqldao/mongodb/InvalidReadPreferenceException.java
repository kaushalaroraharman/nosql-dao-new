package org.eclipse.ecsp.nosqldao.mongodb;

/**
 * Exception class for invalid read preferences in MongoDB operations.
 */
public class InvalidReadPreferenceException extends RuntimeException {

    /**
     * The serial version UID for this class.
     */
    private static final long serialVersionUID = 499901887457659415L;

    /**
     * Constructs a new InvalidReadPreferenceException with the specified detail message.
     *
     * @param s the detail message
     */
    public InvalidReadPreferenceException(String s) {
        super(s);
    }
}
