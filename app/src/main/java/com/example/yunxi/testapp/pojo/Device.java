package com.example.yunxi.testapp.pojo;

public class Device {

    private  String accout;
    private String customFields;
    private int groupId;
    private String name;
    private String devid;
    private String address;
    private int type;
    private int onlineStatus;
    private String account;
    private long create_time;
    private int id;
    private String pass;
    private int  polling_interval;
    private int protocol;
    private String position;
    private int product_model;
    private long update_time;
    private int  weight;

    public Device(String accout, String customFields, int groupId, String name, String devid, String address, int type, int onlineStatus, String account, long create_time, int id, String pass, int polling_interval, int protocol, String position, int product_model, long update_time, int weight) {
        this.accout = accout;
        this.customFields = customFields;
        this.groupId = groupId;
        this.name = name;
        this.devid = devid;
        this.address = address;
        this.type = type;
        this.onlineStatus = onlineStatus;
        this.account = account;
        this.create_time = create_time;
        this.id = id;
        this.pass = pass;
        this.polling_interval = polling_interval;
        this.protocol = protocol;
        this.position = position;
        this.product_model = product_model;
        this.update_time = update_time;
        this.weight = weight;
    }

    public Device() {
    }

    public String getAccout() {
        return accout;
    }

    public void setAccout(String accout) {
        this.accout = accout;
    }

    public String getCustomFields() {
        return customFields;
    }

    public void setCustomFields(String customFields) {
        this.customFields = customFields;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getPolling_interval() {
        return polling_interval;
    }

    public void setPolling_interval(int polling_interval) {
        this.polling_interval = polling_interval;
    }

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getProduct_model() {
        return product_model;
    }

    public void setProduct_model(int product_model) {
        this.product_model = product_model;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Device{" +
                "accout='" + accout + '\'' +
                ", customFields='" + customFields + '\'' +
                ", groupId=" + groupId +
                ", name='" + name + '\'' +
                ", devid='" + devid + '\'' +
                ", address='" + address + '\'' +
                ", type=" + type +
                ", onlineStatus=" + onlineStatus +
                ", account='" + account + '\'' +
                ", create_time=" + create_time +
                ", id=" + id +
                ", pass='" + pass + '\'' +
                ", polling_interval=" + polling_interval +
                ", protocol=" + protocol +
                ", position='" + position + '\'' +
                ", product_model=" + product_model +
                ", update_time=" + update_time +
                ", weight=" + weight +
                '}';
    }
}
