package com.cg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataInputException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataInputException(String message) {
        super(message);
    }

    public DataInputException(Map<String, String> map) {
    }
}