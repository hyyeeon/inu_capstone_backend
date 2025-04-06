package com.capstone.startmap.exception.user;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(final String message) {
        super(message);
    }

    public WrongPasswordException() {
        this("비밀번호가 일치하지 않습니다.");
    }
}