package com.example.yunxi.testapp.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yunxi.testapp.R;
import com.example.yunxi.testapp.global.SPEnum;
import com.example.yunxi.testapp.global.SysConfig;

import static android.content.Context.MODE_PRIVATE;

public class MyFragment4 extends Fragment implements View.OnClickListener {


    TextView tv_username ;
    LinearLayout ll_userInfo;
    Button btn_logout;
    public MyFragment4(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
       /* TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        txt_content.setText("第4个Fragment");*/
        Log.e("HEHE", "4日狗");
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {

        ll_userInfo = view.findViewById(R.id.me_ll_userInfo);
        btn_logout = view.findViewById(R.id.me_btn_logout);
        tv_username = view.findViewById(R.id.me_tv_username);

        ll_userInfo.setOnClickListener(this);
        btn_logout.setOnClickListener(this);

    }
    private void initData() {

        final SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SysConfig.SP_NAME, MODE_PRIVATE);
        String username = sharedPreferences.getString(SPEnum.Username.getKey(),null);
        Log.e("username", "get username : "+username);
        tv_username.setText(username);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_btn_logout:
                Log.e("in onclick","logout button");
                logout();
                break;
        }

    }

    private void logout() {

        Log.e("int logout","clear data ing");

        SharedPreferences preferences = getActivity().getSharedPreferences(SysConfig.SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(SPEnum.Token.getKey());
        editor.remove(SPEnum.Username.getKey());
        editor.apply();
        startActivity(new Intent(getContext(), LoginActivity.class));
        getActivity().finish();
    }
}
