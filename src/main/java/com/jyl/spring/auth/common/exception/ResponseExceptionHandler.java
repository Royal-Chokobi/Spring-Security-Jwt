package com.jyl.spring.auth.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * packageName    : com.jyl.spring.auth.common.exception
 * fileName       : ResponseExceptionHandler
 * author         : leejaeyoon
 * date           : 2022/05/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/24        leejaeyoon       최초 생성
 */
@ControllerAdvice
@Slf4j
public class ResponseExceptionHandler {

    private final String E400 = String.valueOf(HttpStatus.BAD_REQUEST.value());
    private final String E500 = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());


    protected ResponseEntity<Object> responseExceptions(String code, String objname, Exception ex){
        Errors errors = new BeanPropertyBindingResult("Exception", objname);
        try {
            String cause = ex.getMessage();
//            String cause = ex.getCause().getMessage();
            errors.reject(code, cause);
            if(Integer.valueOf(code) >= 500){
                return ResponseEntity.internalServerError().body(errors);
            }
            return ResponseEntity.badRequest().body(errors);
        }catch (Exception exception){
            errors.reject(E500, "System error, please contact your representative.");
            log.error("ResponseExceptions", exception);
            return ResponseEntity.internalServerError().body(errors);
        }
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ResponseEntity<Object> handleMissingRequestHeaderExceptions(MissingRequestHeaderException ex) {
        return responseExceptions(E400, "MissingRequestHeaderException", ex);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadableExceptions(HttpMessageNotReadableException ex) {
        return responseExceptions(E400, "HttpMessageNotReadableException", ex);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupportedExceptions(HttpRequestMethodNotSupportedException ex) {
        return responseExceptions(E400, "HttpRequestMethodNotSupportedException", ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException ex) {
        return responseExceptions(E400, "MethodArgumentNotValidException", ex);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex) {
        return responseExceptions(E500, "Exception", ex);
    }

}
