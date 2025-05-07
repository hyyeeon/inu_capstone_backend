package com.capstone.startmap.exception.Result;

public class NotFoundResultException extends RuntimeException {
    public NotFoundResultException(final String message) {
        super(message);
    }
    public NotFoundResultException() {
        this("결과를 찾을 수 없습니다.");
    }
}
