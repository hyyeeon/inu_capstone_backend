package com.capstone.startmap.exception.building;

public class NotFoundBuildingException extends RuntimeException {
    public NotFoundBuildingException(final String message) { super(message); }
    public NotFoundBuildingException() { this("건물을 찾을 수 없습니다."); }
}
