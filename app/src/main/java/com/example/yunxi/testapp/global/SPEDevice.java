package com.example.yunxi.testapp.global;

public enum SPEDevice {


    DEVICE("LoRa集中器",1);
    private String type;
    private int index;
    // 0：默认设备 1：LoRa集中器  2：CoAP/NB-IoT  3 ：LoRa模块 4：网络io  5：扫码添加  6：LoRaWAN  7.电信 CoAP/NB-IoT

    SPEDevice(String key, int index) {
        this.type = key;
        this.index = index;
    }
    public String getType(int index){

        switch (index){
            case  0:
                return "默认设备";

            case 1:
                return "LoRa集中器";

            case 2:
                return "CoAP/NB-IoT";

            case 3:
                return "LoRa模块";

            case 4:
                return "网络io";

            case 5:
                return "扫码添加";

            case 6:
                return "LoRaWAN";

            case 7 :
                return "电信 CoAP/NB-IoT";

        }
        return "";
    }

}
