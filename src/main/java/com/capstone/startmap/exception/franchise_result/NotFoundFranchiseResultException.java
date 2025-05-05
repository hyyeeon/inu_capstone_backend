package com.capstone.startmap.exception.franchise_result;

public class NotFoundFranchiseResultException extends RuntimeException {
    public NotFoundFranchiseResultException(final String message) { super(message); }
    public NotFoundFranchiseResultException() {this("프랜차이즈 검색 결과를 찾을 수 없습니다."); }
}

