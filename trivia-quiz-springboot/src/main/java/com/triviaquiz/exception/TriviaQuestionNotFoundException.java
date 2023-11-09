package com.triviaquiz.exception;

public class TriviaQuestionNotFoundException extends RuntimeException {
    public TriviaQuestionNotFoundException(String message) {
        super(message);
    }
}