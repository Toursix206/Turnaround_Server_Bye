package com.toursix.turnaround.common.exception;

public class UnAuthorizedException extends BoilerplateException {

    public UnAuthorizedException(String message) {
        super(message, ErrorCode.UNAUTHORIZED_EXCEPTION);
    }
}
