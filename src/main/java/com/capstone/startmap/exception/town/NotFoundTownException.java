package com.capstone.startmap.exception.town;

public class NotFoundTownException extends RuntimeException {
    public NotFoundTownException(final String message) {
        super(message);
    }
    public NotFoundTownException() {
        this("동을 찾을 수 없습니다.");
    }
}
