package com.toursix.turnaround.common.exception;

public class UnAuthorizedException extends TurnaroundException {

    public UnAuthorizedException(String message) {
        super(message, ErrorCode.UNAUTHORIZED_EXCEPTION);
    }
}
