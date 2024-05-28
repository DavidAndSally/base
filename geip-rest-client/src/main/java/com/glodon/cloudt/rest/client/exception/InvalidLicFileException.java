package com.glodon.cloudt.rest.client.exception;

/**
 * 非法授权文件异常
 * <p/>
 * Created by chenxy on 2015-11-05.
 */
public class InvalidLicFileException extends NoAuthenticateException {
    public InvalidLicFileException(String errorMessage) {
        super(errorMessage);
    }
}
