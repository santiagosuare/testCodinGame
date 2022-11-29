package com.plexus.testCodinGame.model.service.exception;

import org.springframework.http.HttpStatus;

/*
 * Base Service Exception
 */
public class ServiceException  extends RuntimeException{

    private static final long serialVersionUID = 71263789126493124L;

    private final HttpStatus code;

    public ServiceException(final String message){
        super(message);
        code = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
        code = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Creates a new isntance of {@link ServiceException}
     *
     * @param message the exception message
     * @param code the {@link HttpStatus} used that will be used in the REST response
     */
    public ServiceException(String message, final HttpStatus code) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause, HttpStatus code) {
        super(message, cause);
        this.code = code;
    }
}
