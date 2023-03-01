package com.rainng.coursesystem.teacher.util;

public class JsonResult<T> {
    /*
    * 返回值封装
    * */
    private int code;       //状态码
    private String msg;     //返回的提示信息
    private T data;         //返回的数据

    //利用构造函数  设置默认  请求成功
    //1.如果请求不需要返回值，只需要提示成功或者失败
    public JsonResult(){
        this.code=0;
        this.msg="请求成功";
    }
    //2.如果没有返回值，需要自定义状态码和提示信息
    public JsonResult(int code, String msg){
        this.code=code;
        this.msg=msg;
    }
    //3.有返回值和状态码，默认信息是请求成功
    public JsonResult(T data){
        this.code=0;
        this.msg="请求成功";
        this.data=data;
    }
    //4.有返回信息，状态码，提示的信息自定义
    public JsonResult(T data, String msg){
        this.code=0;
        this.msg=msg;
        this.data=data;
    }

    //get  set
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

    public JsonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
