package com.dianwoda.test.bassy.web.config;

import com.alibaba.fastjson.JSONObject;
import com.dianwoda.test.bassy.common.enums.ErrorCode;
import com.dianwoda.test.bassy.common.exception.BusinessException;
import com.dianwoda.test.bassy.common.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zcp
 * @date 2019/3/11 11:04
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = BusinessException.class)
    public CommonResponse businessExceptionHandler(HttpServletRequest request, BusinessException e) {
        logger.error("Business Exception:errorCode:{0},errorMsg:{1}",e.getErrorCode(),e.getMessage());
        return new CommonResponse(ErrorCode.FAIL,e.getMessage(),null);
    }
    @ExceptionHandler(value = Exception.class)
    public CommonResponse exceptionHandler(HttpServletRequest request,Exception e) {
        logger.error("Internal Exception:",e);
        return CommonResponse.fail(e.getCause().getMessage());
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("MethodArgumentNotValid( Exception:",ex);
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(entity -> (entity.getField()+" "+ entity.getDefaultMessage()))
                .findFirst()
                .orElse(ex.getMessage());
        return response(ex, headers,request, HttpStatus.BAD_REQUEST, JSONObject.toJSONString(CommonResponse.fail(errorMessage)));
    }
    private ResponseEntity<Object> response(Exception ex,HttpHeaders headers, WebRequest request, HttpStatus status,
                                            String message) {
        return handleExceptionInternal(ex, message, headers, status, request);
    }
}
