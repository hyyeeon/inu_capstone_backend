package com.capstone.startmap.exception.franchise;

public class NotFoundFranchiseException extends RuntimeException {
    public NotFoundFranchiseException(final String message) { super(message); }
    public NotFoundFranchiseException() {this("프랜차이즈를 찾을 수 없습니다."); }
}
