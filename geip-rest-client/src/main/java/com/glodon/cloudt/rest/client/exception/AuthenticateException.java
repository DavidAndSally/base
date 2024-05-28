package com.glodon.cloudt.rest.client.exception;

/**
 * Created by chenxy on 2016/3/14.
 */
public class AuthenticateException extends Exception {
    public AuthenticateException(String errorMessage) {
        super(errorMessage);
    }

    public AuthenticateException(String message, Throwable cause) {
        super(message, cause);
    }
}

