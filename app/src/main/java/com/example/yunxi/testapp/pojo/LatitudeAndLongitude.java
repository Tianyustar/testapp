package com.example.yunxi.testapp.pojo;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * create by liuyunxng
 * 2018 6 4
 * function：
 *  latitude and longitude
 *  string format ： 111，111  （laitutude， longitude）；
 */
public class LatitudeAndLongitude implements Serializable {

    private String strPosition;
    private double latitude;
    private double longitude;

    public LatitudeAndLongitude(String strPosition, double latitude, double longtitude) {
        this.strPosition = strPosition;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LatitudeAndLongitude() {
    }

    public LatitudeAndLongitude(String strPosition) {
        this.strPosition = strPosition;
        Map<String , Double> res = this.getLatitudeAndLongitude(strPosition);
        this.latitude = res.get("latitude");
        this.longitude = res.get("longitude");
    }

    public LatitudeAndLongitude(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.strPosition = this.getStrPosition(latitude, longitude);
    }

    public String getStrPosition(double latitude, double longitude){

        return latitude +" , " + longitude;
    }

    private Map getLatitudeAndLongitude(String strPosition) {

        Map<String, Double> res = new HashMap<>();
        String[] latitudeAndLongitude = strPosition.split(",");
        if (latitudeAndLongitude.length == 2){
            double longitude  = Double.parseDouble(latitudeAndLongitude[0]);
            double  latitude =  Double.parseDouble(latitudeAndLongitude[1]);
            res.put("latitude", latitude);
            res.put("longitude", longitude);
            return res;
        }
        return  null;
    }

    public String getStrPosition() {
        return strPosition;
    }

    public void setStrPosition(String strPosition) {
        this.strPosition = strPosition;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getlongitude() {
        return longitude;
    }

    public void setlongitude(int longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "LatitudeAndLongitude{" +
                "strPosition='" + strPosition + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
