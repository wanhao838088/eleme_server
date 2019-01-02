package com.wanhao.eleme.bean;

/**
 * 标准JSON响应消息模板
 * @author Administrator
 */
public class JsonResult {
    private Integer code;
    private String msg;
    private Object data;

    public Integer getCode() {
        return code;
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

    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public JsonResult(Integer code, String msg,Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public JsonResult(Integer code) {
        this.code = code;
        if(code == 0)  this.msg = "success";
        if(code == 1)  this.msg = "error";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
