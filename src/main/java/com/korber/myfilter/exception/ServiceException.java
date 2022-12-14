package com.korber.myfilter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ServiceException extends RuntimeException{
    public ServiceException() {
        super();
    }
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public ServiceException(String message) {
        super(message);
    }
    public ServiceException(Throwable cause) {
        super(cause);
    }

}
