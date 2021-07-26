package com.mask.ssm.response;

/**
 * @author: Mask.m
 * @create: 2021/07/26 21:02
 * @description: 统一响应
 */
public class ResponseResult<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 返回的数据信息
     */
    private T data;


    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
