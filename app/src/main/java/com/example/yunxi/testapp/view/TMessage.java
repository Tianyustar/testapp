package com.example.yunxi.testapp.view;

import com.example.yunxi.testapp.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by tianyu on 17-7-5.
 */
@HttpResponse(parser = JsonResponseParser.class)

public class TMessage {
    private int code;
    private int status;
    private String info;
    private Object data;

    public static int CODE_SUCCESS = 1;
    public static int CODE_FAILURE = -1;
    public static int STATUS_SUCCESS = 0;

    public TMessage() {

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public TMessage(int code, int status, String info) {
        this.status =  status;
        this.code = code;
        this.info = info;
    }

    public TMessage(int code, int status, String info, Object data) {
        this.status =  status;
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TMessage{" +
                "code=" + code +
                ", status=" + status +
                ", info='" + info + '\'' +
                ", data=" + data +
                '}';
    }
}
