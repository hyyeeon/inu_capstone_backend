package com.capstone.startmap.exception.user;

public class DuplicatedNicknameException extends RuntimeException {
    public DuplicatedNicknameException(final String message) {
        super(message);
    }

    public DuplicatedNicknameException() {
        this("이미 존재하는 닉네임입니다.");
    }
}