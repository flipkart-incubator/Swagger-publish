package com.core.config;

/**
 * Created by shubham.tyagi on 08/05/15.
 */
public class GenerateException extends Exception {

    public GenerateException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public GenerateException(String errorMessage) {
        super(errorMessage);
    }

    public GenerateException(Exception e) {
        super(e);
    }
}
