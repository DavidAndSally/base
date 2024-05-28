package com.glodon.cloudt.rest.client.exception;

/**
 * Created by chenxy on 2016/3/14.
 */
public class NoAuthenticateException extends Exception {
    public NoAuthenticateException(String errorMessage) {
        super(errorMessage);
    }
}
