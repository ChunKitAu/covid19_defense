package com.oujunjie.covid19_defense.comm.my_exception.exceptions;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */
public class AuthorizationException extends RuntimeException {

    public AuthorizationException() {
    }

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
