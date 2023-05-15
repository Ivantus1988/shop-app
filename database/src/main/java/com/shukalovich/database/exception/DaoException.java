package com.shukalovich.database.exception;

public class DaoException extends RuntimeException {
    public DaoException (Throwable throwable) {
        super(throwable);
    }
}
