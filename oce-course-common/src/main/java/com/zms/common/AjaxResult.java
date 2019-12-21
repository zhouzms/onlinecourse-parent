package com.zms.common;

import java.util.List;

/**
 * @author zms
 * Date: 2019/12/1 14:33
 */
public class AjaxResult<T>{
    private String code;
    private String msg;
    private List<T> data;
    public AjaxResult(){
        this.code="200";
        this.msg="ok";
        this.data=null;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
