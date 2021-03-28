package com.oujunjie.covid19_defense.comm.my_exception.exceptions;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */
public class UserException extends RuntimeException {

    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
