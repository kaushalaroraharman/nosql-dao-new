package org.eclipse.ecsp.nosqldao.spring.config;

/**
 * Exception class for MongoDB connection errors.
 */
public class MongoConnectionException extends RuntimeException {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 4559018874596925L;

    /**
     * Constructor for MongoConnectionException.
     *
     * @param msg the error message
     * @param e the exception
     */
    public MongoConnectionException(String msg, Exception e) {
        super(msg, e);
    }
}
