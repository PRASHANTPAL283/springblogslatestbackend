package com.SpringBlogsLatestBE.SpringBlogsLatestBE.ErrorHandler;

public class GlobalExceptionClass extends RuntimeException{

    private final String code;

    public GlobalExceptionClass(String code) {
        super();
        this.code = code;
    }

    public GlobalExceptionClass(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public GlobalExceptionClass(String message, String code) {
        super(message);
        this.code = code;
    }

    public GlobalExceptionClass(Throwable cause, String code) {
        super(cause);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
