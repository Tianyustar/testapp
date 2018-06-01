package com.example.yunxi.testapp.view;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.yunxi.testapp.R;
import com.example.yunxi.testapp.business.UsrCloudClientService;
import com.example.yunxi.testapp.global.SPEnum;
import com.example.yunxi.testapp.global.SysConfig;;
import static com.example.yunxi.testapp.base.UsrApplication.USERNAME;
import  com.example.yunxi.testapp.base.UsrBaseActivity;
import com.example.yunxi.testapp.global.URLEnum;
import com.example.yunxi.testapp.util.MD5Util;
import com.example.yunxi.testapp.util.TBaseCallback;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity   extends UsrBaseActivity implements View.OnClickListener {

    private EditText et_username, et_pwd;
    private Button btn_login, btn_reg;
    private SharedPreferences sharedPreferences;
    private Receiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("in  LoginActivity","Login layout activity");
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(SysConfig.SP_NAME, MODE_PRIVATE);
        et_username = findViewById(R.id.login_et_username);
        et_pwd = findViewById(R.id.login_et_pwd);

        btn_login = findViewById(R.id.login_btn_login);
        btn_reg = findViewById(R.id.login_btn_reg);
        btn_reg.setOnClickListener(this);
        btn_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_btn_reg:

            case R.id.login_btn_login:
                et_username.setError(null);
                et_pwd.setError(null);
                String username = et_username.getText().toString();
                String pwd = et_pwd.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    et_username.setError(getString(R.string.field_required));
                    et_username.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    et_pwd.setError(getString(R.string.password_invalid));
                    et_pwd.requestFocus();
                    return;
                }
                login(username, pwd);

        }

    }

    private void login(final String username, final String pwd) {


        HashMap<String,String> jsonMap = new HashMap<>();  // produce json format params
        jsonMap.put("account",username);
        jsonMap.put("password",MD5Util.getMD5(pwd));
        RequestParams params = new RequestParams(URLEnum.Login.getUrl());
        params.setAsJsonContent(true);
        params.setBodyContent(JSON.toJSONString(jsonMap));
    /*      final ProgressDialog waitingDialog = new ProgressDialog(LoginActivity.this);


        waitingDialog.setTitle("登录中...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);
        waitingDialog.show();*/

        x.http().post(params, new TBaseCallback<TMessage>() {
            @Override
            public void onSuccess(TMessage result) {
                if (result.getStatus() == TMessage.STATUS_SUCCESS){
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    Log.e("ok message",result.getData().toString());

                    editor.putString(SPEnum.Token.getKey(), JSON.parseObject(result.getData().toString(), Map.class).get("token") +"");
                    editor.putString(SPEnum.Username.getKey(), username);
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Log.e("in log", "connectUSr");
                    connectUSR(username, pwd);
                   // startActivity(new Intent(LoginActivity.this, MainActivity.class));
                   // finish();
                }else{
                    Log.e("fail message",result.getCode() +"");
                    Log.e("getMessage", result.toString());
                    Log.e("fail message",result.getData().toString());
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }

            }
        });
       /* Bundle bundle = new Bundle();
        bundle.putString("uname",username);
        bundle.putString("upw",pwd);
        USERNAME = username;
        Log.e("login,.....","login");
        startServiceWithParm(UsrCloudClientService.class, bundle);
        Log.e("after login,.....","login");*/
    }

    public void connectUSR(String username, String pwd){
        Bundle bundle = new Bundle();
        bundle.putString("uname", username);
        bundle.putString("upw", pwd);
        USERNAME=username;
        startServiceWithParm(UsrCloudClientService.class, bundle);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
        setListener();
        receiver = new Receiver();
        //新添代码，在代码中注册广播接收程序
        IntentFilter filter = new IntentFilter();
        Log.e("onstart","login");
        filter.addAction("onConnectAck");
        registerReceiver(receiver, filter);
    }

    public class Receiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra("onConnectAckreturnCode", 1) == 0) {
                Toast.makeText(LoginActivity.this, "建立连接成功，可订阅设备", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else if (intent.getIntExtra("onConnectAckreturnCode", 1) == 1) {
                Toast.makeText(LoginActivity.this, "连接失败， 不可订阅设备", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(receiver);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
