package com.plexus.testCodinGame.model.service.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ServiceException{

    private static final long serialVersionUID = 2347859384659834L;

    public BadRequestException(final String message){
        super(message, HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(final String message, final Throwable cause){
        super(message, cause, HttpStatus.BAD_REQUEST);
    }

}
