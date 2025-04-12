package com.capstone.startmap.exception.user;

public class EmailMismatchException extends RuntimeException {
    public EmailMismatchException(final String message) {
        super(message);
    }

    public EmailMismatchException() {
        this("이메일이 일치하지 않습니다.");
    }
}
