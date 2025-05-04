package com.capstone.startmap.exception.user;

public class NotFoundUserException extends RuntimeException{
    public NotFoundUserException(final String message) {
        super(message);
    }

    public NotFoundUserException() {
        this("멤버를 찾을 수 없습니다.");
    }
}