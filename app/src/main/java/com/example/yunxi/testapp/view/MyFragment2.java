package com.example.yunxi.testapp.view;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.yunxi.testapp.R;
import com.example.yunxi.testapp.adapter.TAdapter;
import com.example.yunxi.testapp.global.SPEDevice;
import com.example.yunxi.testapp.global.SPEnum;
import com.example.yunxi.testapp.global.SysConfig;
import com.example.yunxi.testapp.global.URLEnum;
import com.example.yunxi.testapp.pojo.Device;
import com.example.yunxi.testapp.pojo.RespondDevice;
import com.example.yunxi.testapp.util.TBaseCallback;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MyFragment2 extends Fragment {


    private Context mContext;
    private ListView list_allDev;
    private ListView list_onlineDev;
    private TAdapter<Device> alldeviceTAdapter;
    private TAdapter<Device> onlineDeviceTAdapter;
    private List<Device>  alldeviceList;
    private List<Device>  onlineDeviceList;
    public MyFragment2(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        initViews(view);
        Log.e("HEHE", "2日狗");
        return view;
    }

    private void initViews(View view) {

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SysConfig.SP_NAME, MODE_PRIVATE);
        String token = sharedPreferences.getString(SPEnum.Token.getKey(),null);

        mContext = this.getActivity();
        list_allDev = view.findViewById(R.id.list_alldev);
        list_onlineDev = view.findViewById(R.id.list_ondev);
        alldeviceList = new ArrayList<Device>();
        onlineDeviceList = new ArrayList<Device>();
        getInfo(token);

    }

    private TAdapter<Device>  getListAdapter(ArrayList<Device> alldeviceList, int id){

        return new TAdapter<Device>(alldeviceList, id) {
            @Override
            public void bindView(ViewHolder holder, Device obj) {
                holder.setText(R.id.tv_itme_devname, obj.getName());
                holder.setText(R.id.tv_itme_devid, "设备id： "+obj.getDevid());
                holder.setText(R.id.tv_itme_devaddress,"设备地址： "+ obj.getAddress());
                holder.setText(R.id.tv_itme_devtype, "设备类型： "+SPEDevice.DEVICE.getType(obj.getType()));
                holder.setText(R.id.tv_itme_devstatus, obj.getOnlineStatus() == 0? "离线":"在线");
            }
        };
    }

    private void getInfo(String token ) {


        RequestParams params = new RequestParams(URLEnum.GetAllDevice.getUrl());
        HashMap<String ,String> jsonContent = new HashMap<>();
        jsonContent.put("token",token);
        params.setAsJsonContent(true);
        params.setBodyContent(JSON.toJSONString(jsonContent));

        x.http().post(params ,new TBaseCallback<TMessage>(){

            @Override
            public void onSuccess(TMessage result) {
                if (result.getStatus() == TMessage.STATUS_SUCCESS){

                    RespondDevice reInfo = JSON.toJavaObject((JSON) result.getData(),RespondDevice.class);
                    Log.e("get all xhtp list ",  reInfo.getDev().size() +  reInfo.getDev().toString());
                    alldeviceList = reInfo.getDev();
                    Log.e("get all xhttp list ", alldeviceList.size() + alldeviceList.toString());

                    for (Device device : alldeviceList){
                        if (device.getOnlineStatus()  == 1){
                            onlineDeviceList.add(device);
                        }
                    }

                    alldeviceTAdapter = getListAdapter((ArrayList<Device>) alldeviceList, R.layout.item_one);
                    onlineDeviceTAdapter = getListAdapter((ArrayList<Device>) onlineDeviceList, R.layout.item_one);
                    list_allDev.setAdapter(alldeviceTAdapter);
                    list_onlineDev.setAdapter(onlineDeviceTAdapter); // 为了做测试
                    Log.e("get xhttp online list ", onlineDeviceList.size() + onlineDeviceList.toString());


                }
            }
        });

        // 获取离线设备数量


    }
}
