package com.example.yunxi.testapp.view;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.example.yunxi.testapp.R;
import com.example.yunxi.testapp.pojo.LatitudeAndLongitude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class BaiduMapActivity extends AppCompatActivity {


    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    HashMap mapPoint;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidu_map);
        initViews();
        initGenerator();  // 显示本地位置
        showPointInMap();


    }

    private void initViews() {
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        Intent intent = getIntent();
        mapPoint = (HashMap) intent.getSerializableExtra("mapPoint");
        Log.e("mapPoint", mapPoint.size() +"");

    }

    private void showPointInMap(){

        List<OverlayOptions> options = new ArrayList<OverlayOptions>();
        Iterator iter = mapPoint.entrySet().iterator();
        while(iter.hasNext()){

            Log.e("in iter", "inInter");
            View view = View.inflate(BaiduMapActivity.this, R.layout.layout_icon, null);
            TextView iconText = view.findViewById(R.id.icon_name);
            ImageView iconImage = view.findViewById(R.id.icon_image);
            Map.Entry entry  = (Map.Entry) iter.next();
            CoordinateConverter converter  = new CoordinateConverter();
            converter.from(CoordinateConverter.CoordType.GPS);
            LatitudeAndLongitude lAndLong = (LatitudeAndLongitude) entry.getValue();
            LatLng point = new LatLng(lAndLong.getLatitude(), lAndLong.getlongitude());
            converter.coord(point);
            LatLng convertPoint = converter.convert();
            iconText.setText((String)entry.getKey());
            iconImage.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_position_dev));
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(view);


            Log.e("in iter", (String)entry.getKey());
            Log.e("in iter", entry.getValue().toString());
            OverlayOptions option = new MarkerOptions()
                    .position(convertPoint)
                    .icon(bitmap)
                    .title("place")
                    .animateType(MarkerOptions.MarkerAnimateType.grow)
                    .visible(true);
            options.add(option);



        }

        mBaiduMap.addOverlays(options);

    }

    private void initGenerator() {

        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
//可选，设置返回经纬度坐标类型，默认gcj02
//gcj02：国测局坐标；
//bd09ll：百度经纬度坐标；
//bd09：百度墨卡托坐标；
//海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标

        option.setScanSpan(1000);
//可选，设置发起定位请求的间隔，int类型，单位ms
//如果设置为0，则代表单次定位，即仅定位一次，默认为0
//如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
//可选，设置是否使用gps，默认false
//使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
//可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
//可选，定位SDK内部是一个service，并放到了独立进程。
//设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
//可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5 * 60 * 1000);
//可选，7.2版本新增能力
//如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位

        option.setEnableSimulateGps(false);
//可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        mLocationClient.setLocOption(option);
//mLocationClient为第二步初始化过的LocationClient对象
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        mLocationClient.start();



    } // 设置本地标签

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }



    public  class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
            String coorType = location.getCoorType();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            int errorCode = location.getLocType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明

            Log.e("latitue", latitude + "");
            Log.e("longitude", longitude + "");

            LatLng point = new LatLng(latitude, longitude);

//构建Marker图标

            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_my_position);

//构建MarkerOption，用于在地图上添加Marker

            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap)
                    .visible(true);
//在地图上添加Marker，并显示

            mBaiduMap.addOverlay(option);
            MapStatus mapStatus = new MapStatus.Builder()
                    .target(point)
                    .zoom(12)
                    .build();
            //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
            //改变地图状态
            mBaiduMap.setMapStatus(mMapStatusUpdate);


        }
    } // 监听获得本地地址

}
