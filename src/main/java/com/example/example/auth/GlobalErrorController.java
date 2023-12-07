package com.example.example.auth;

import com.example.example.RespResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 全局异常处理
@Slf4j
@Controller
@RestControllerAdvice
@RequestMapping("${server.error.path:${error.path:/error}}")
public class GlobalErrorController extends AbstractErrorController {

    public GlobalErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping
    public RespResult<String> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        return RespResult.fail(status.getReasonPhrase());
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public RespResult<String> mediaTypeNotAcceptable(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        return RespResult.fail(status.getReasonPhrase());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public RespResult<String> badCredentialsException(Exception e) {
        return RespResult.fail(e.getMessage());
    }
}