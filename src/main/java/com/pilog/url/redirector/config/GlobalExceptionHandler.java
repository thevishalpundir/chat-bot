package com.pilog.url.redirector.config;


import com.pilog.url.redirector.dto.ErrorResponseDto;
import com.pilog.url.redirector.exception.BadRequestException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleNoSuchElementException(BadRequestException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body(getErrorResponse(ex.getMessage(), ex.getRawStatusCode(), ex.getStatusText()));
    }

    public static ErrorResponseDto getErrorResponse(
            String message, Integer statusCode, String errorCode) {
        return ErrorResponseDto.builder()
                .message(message)
                .timestamp(getCurrentDateTime())
                .status(statusCode)
                .errorCode(errorCode)
                .build();
    }

    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
