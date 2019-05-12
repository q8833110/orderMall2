package com.order.mall.data.network.shop;

import java.util.List;

public class ShopOrder {
    /**
     * address : string
     * deliveryTime : 2019-05-12T01:21:55.037Z
     * freight : 0
     * id : string
     * mobile : string
     * name : string
     * num : 0
     * payTime : 2019-05-12T01:21:55.038Z
     * picture : string
     * pictures : ["string"]
     * price : 0
     * reciver : string
     * status : 0
     * userId : 0
     */

    private String address;
    private String deliveryTime;
    private int freight;
    private String id;
    private String mobile;
    private String name;
    private int num;
    private String payTime;
    private String picture;
    private int price;
    private String reciver;
    private int status;
    private int userId;
    private List<String> pictures;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
