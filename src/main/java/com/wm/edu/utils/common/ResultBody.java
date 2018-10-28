package com.wm.edu.utils.common;

public class ResultBody {
    private int code=200;//默认为200

    private String message;//返回中文信息

    private Object data;//返回数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {

        message=StauteEnum.getName(code);
        if(message==null){
            message="未知异常！";
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
