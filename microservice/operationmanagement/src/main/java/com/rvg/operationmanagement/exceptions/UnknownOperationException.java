package com.rvg.operationmanagement.exceptions;

public class UnknownOperationException extends RuntimeException {
    public UnknownOperationException(String message) {
        super(message);
    }
}
