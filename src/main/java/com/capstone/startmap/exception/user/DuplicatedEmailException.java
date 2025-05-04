package com.capstone.startmap.exception.user;

public class DuplicatedEmailException extends RuntimeException {
    public DuplicatedEmailException(final String message) {
        super(message);
    }

    public DuplicatedEmailException() {
        this("이미 존재하는 이메일입니다.");
    }
}