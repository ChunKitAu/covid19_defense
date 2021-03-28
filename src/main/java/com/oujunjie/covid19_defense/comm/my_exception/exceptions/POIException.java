package com.oujunjie.covid19_defense.comm.my_exception.exceptions;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */
public class POIException extends RuntimeException {

    public POIException() {
    }

    public POIException(String message) {
        super(message);
    }

    public POIException(String message, Throwable cause) {
        super(message, cause);
    }
}
