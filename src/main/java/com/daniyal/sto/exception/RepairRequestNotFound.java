package com.daniyal.sto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RepairRequestNotFound extends RuntimeException {
    public RepairRequestNotFound(String message) {
        super(message);
    }
}
