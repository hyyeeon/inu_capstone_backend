package com.capstone.startmap.exception.location_result;

public class NotFoundLocationResultException extends RuntimeException {
    public NotFoundLocationResultException(final String message) { super(message); }
    public NotFoundLocationResultException() {this("위치 검색 결과를 찾을 수 없습니다."); }
}
