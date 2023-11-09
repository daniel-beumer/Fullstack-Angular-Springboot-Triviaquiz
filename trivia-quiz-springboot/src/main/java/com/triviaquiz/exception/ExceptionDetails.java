package com.triviaquiz.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
class ExceptionDetails {
    protected String title;
    protected int status;
    protected String detail;
    protected LocalDateTime timestamp;
}