package com.pilog.url.redirector.exception;


import java.nio.charset.Charset;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class BadRequestException extends HttpStatusCodeException {

    private static final long serialVersionUID = -123456789L;

    public BadRequestException(HttpStatus errorCode, String message, String statusText) {
        super(message, errorCode, statusText, (HttpHeaders) null, (byte[]) null, (Charset) null);
    }
}
