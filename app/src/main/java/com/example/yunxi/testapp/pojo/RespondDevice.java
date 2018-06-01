package com.example.yunxi.testapp.pojo;

import java.util.List;

public class RespondDevice {
    List<Device> dev;
    int total;

    public RespondDevice() {
    }

    public List<Device> getDev() {
        return dev;
    }

    public void setDev(List<Device> dev) {
        this.dev = dev;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "RespondDevice{" +
                "dev=" + dev +
                ", total=" + total +
                '}';
    }
}
