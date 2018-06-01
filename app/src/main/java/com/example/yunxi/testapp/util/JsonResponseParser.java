package com.example.yunxi.testapp.util;

import com.alibaba.fastjson.JSON;
import com.example.yunxi.testapp.view.TMessage;

import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

import java.lang.reflect.Type;

/**
 * Created by 天宇 on 2018/1/2.
 */

public class JsonResponseParser implements ResponseParser {
    @Override
    public void checkResponse(UriRequest request) throws Throwable {

    }

    @Override
    public Object parse(Type resultType, Class<?> resultClass, String result) throws Throwable {
        if (resultClass.equals(TMessage.class)) {
            return JSON.parseObject(result, TMessage.class);
        } else
            return null;
    }
}
