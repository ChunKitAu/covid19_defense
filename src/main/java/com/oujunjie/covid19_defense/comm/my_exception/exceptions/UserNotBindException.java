package com.oujunjie.covid19_defense.comm.my_exception.exceptions;

/**
 * @auther ChunKitAu
 * @create 2021-03-29 29
 */
public class UserNotBindException extends RuntimeException {

    public UserNotBindException() {
    }

    public UserNotBindException(String message) {
        super(message);
    }

    public UserNotBindException(String message, Throwable cause) {
        super(message, cause);
    }
}
