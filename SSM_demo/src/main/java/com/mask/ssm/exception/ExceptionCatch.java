package com.mask.ssm.exception;

import com.mask.ssm.response.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Mask.m
 * @create: 2021/07/26 21:45
 * @description: 异常通用处理
 */
@ControllerAdvice
@ResponseBody
public class ExceptionCatch {

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionCatch(){
        return new ResponseResult(500,"服务器内部错误，请稍后再试");
    }
}
