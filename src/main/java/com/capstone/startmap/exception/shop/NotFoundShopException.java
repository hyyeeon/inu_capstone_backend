package com.capstone.startmap.exception.shop;

public class NotFoundShopException extends RuntimeException {
    public NotFoundShopException(final String message) {
        super(message);
    }
    public NotFoundShopException() { this("매장을 찾을 수 없습니다."); }
}
