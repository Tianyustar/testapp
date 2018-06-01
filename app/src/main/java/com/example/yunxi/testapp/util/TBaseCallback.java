package com.example.yunxi.testapp.util;

import android.util.Log;

import org.xutils.common.Callback;

/**
 * Created by 天宇 on 2018/1/2.
 */

public abstract class TBaseCallback<T> implements Callback.CommonCallback<T> {
    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        Log.e("error", ex.getLocalizedMessage());
    }
}
