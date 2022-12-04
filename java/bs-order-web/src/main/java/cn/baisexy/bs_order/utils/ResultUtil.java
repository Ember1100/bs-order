package cn.baisexy.bs_order.utils;


import cn.baisexy.bs_order.api.pojo.send.ReturnCode;
import lombok.Data;

@Data
public class ResultUtil<T> {
    private int status;
    private String message;
    private T data;

    private String token;

    public ResultUtil(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResultUtil(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ResultUtil(ReturnCode returnCode) {
        this.status = returnCode.statusCode;
        this.message = returnCode.msg;
    }

    public ResultUtil(ReturnCode returnCode,String msg) {
        this.status = returnCode.statusCode;
        this.message = msg;
    }

    public ResultUtil(T data, ReturnCode code, String token) {
        this.data = data;
        status = code.statusCode;
        this.message = code.msg;
        this.token = token;
    }


    public ResultUtil(T data, ReturnCode code) {
        this.data = data;
        status = code.statusCode;
        this.message = code.msg;
    }




}
