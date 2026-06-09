package com.example.productorder.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 전역 예외 처리 클래스
 *
 * 애플리케이션에서 발생하는 예외를 한 곳에서 처리한다.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 잘못된 요청 또는 존재하지 않는 데이터 조회 시 발생하는 예외 처리
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgumentException(
            IllegalArgumentException exception
    ) {
        return new ErrorResponse(exception.getMessage());
    }
}