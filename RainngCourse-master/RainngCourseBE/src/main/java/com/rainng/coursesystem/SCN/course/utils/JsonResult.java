package com.rainng.coursesystem.SCN.course.utils;

public class JsonResult<T> {
    private int code;//0 1 -1
    private String msg;
    private T data;
    //默认成功
    //不需要返回值，只需要提示成功或者失败
    public JsonResult(){
        this.code=0;
        this.msg="请求成功";
    }

    //如果没有返回，需要自定义状态码和提示信息
    public JsonResult(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    //有返回值和状态码，默认信息是请求成功
    public JsonResult(T data){
        this.code=0;
        this.msg="请求成功";
        this.data=data;
    }

    //提示信息自定义
    public JsonResult(T data,String msg){
        this.code=0;
        this.msg=msg;
        this.data=data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
