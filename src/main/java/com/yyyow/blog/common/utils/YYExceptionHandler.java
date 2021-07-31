package com.yyyow.blog.common.utils;


import cn.hutool.core.exceptions.ValidateException;
import com.yyyow.blog.common.config.YYException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

@SuppressWarnings({"ALL", "AlibabaClassNamingShouldBeCamel"})
@Slf4j
@ControllerAdvice
public class YYExceptionHandler {

    /**
     * 全局捕获异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(YYException.class)
    public R errorHandler(HttpServletRequest request, YYException ex) {
        log.error("exception: url {} err {}",request.getRequestURI(), ex.getR());
        R r = ex.getR();
        return r;
    }

    @ResponseBody
    @ExceptionHandler(ValidateException.class)
    public R errorHandler(HttpServletRequest request, ValidateException ex) {
        log.error("exception: url {} err {}",request.getRequestURI(),ex.getMessage());
        R r = R.error(R.FAILURE,ex.getMessage());
        return r;
    }

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    public R errorHandler(HttpServletRequest request, ValidationException ex) {
        log.error("exception: url {} err {}",request.getRequestURI(),ex.getMessage());
        R r = R.error(R.FAILURE,ex.getMessage());
        return r;
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    public R errorHandler(HttpServletRequest request, BindException ex) {
        log.error("exception: url {} err {}",request.getRequestURI(),ex.getMessage());
        StringBuilder sb = new StringBuilder();
        for (ObjectError allError : ex.getAllErrors()) {
            sb.append(allError.getDefaultMessage()+",");
        }
        R r = R.error(R.FAILURE,sb.toString());
        return r;
    }

    /**
     * 默认全局异常
     * @param request
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R handleException(HttpServletRequest request, Throwable ex) {
        String uri = request.getRequestURI();
        if (request.getQueryString() != null) {
            uri += '?' + request.getQueryString();
        }
        String url = String.format("%s %s", request.getMethod(), uri);
        String message = ex.getLocalizedMessage();
        log.error("err url {} msg {} stace {}", url, message, ex.getStackTrace());
        return R.error(R.FAILURE,"出现异常");
    }

}