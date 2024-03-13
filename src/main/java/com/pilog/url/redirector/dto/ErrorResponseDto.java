package com.pilog.url.redirector.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {
    private String message;
    private String timestamp;
    private Integer status;
    private String errorCode;
}
