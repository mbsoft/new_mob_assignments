package com.detroitlabs.skateboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SkateBoardNotFoundException extends RuntimeException {
    public SkateBoardNotFoundException(String message) {
        super(message);
    }
}
