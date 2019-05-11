package com.order.mall.data.network.user;

import java.util.List;

public class ShoppingList {

    /**
     * total : 7
     * size : 10
     * pages : 1
     * pageNum : 1
     * data : [{"id":"575657675430297600","userId":500000,"name":"234234","picture":"http://tcmall.oss-cn-shenzhen.aliyuncs.com/imgs/201905060224512736781370.gif","pictures":null,"price":234,"freight":0.45,"num":1,"status":1,"payTime":"2019-05-08 12:18:05","deliveryTime":null,"reciver":"hello","mobile":"13540300510","address":"成都市红灯区"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 575657675430297600
         * userId : 500000
         * name : 234234
         * picture : http://tcmall.oss-cn-shenzhen.aliyuncs.com/imgs/201905060224512736781370.gif
         * pictures : null
         * price : 234
         * freight : 0.45
         * num : 1
         * status : 1
         * payTime : 2019-05-08 12:18:05
         * deliveryTime : null
         * reciver : hello
         * mobile : 13540300510
         * address : 成都市红灯区
         */

        private String id;
        private int userId;
        private String name;
        private String picture;
        private Object pictures;
        private double price;
        private double freight;
        private int num;
        private int status;
        private String payTime;
        private Object deliveryTime;
        private String reciver;
        private String mobile;
        private String address;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public Object getPictures() {
            return pictures;
        }

        public void setPictures(Object pictures) {
            this.pictures = pictures;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getFreight() {
            return freight;
        }

        public void setFreight(double freight) {
            this.freight = freight;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public Object getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(Object deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public String getReciver() {
            return reciver;
        }

        public void setReciver(String reciver) {
            this.reciver = reciver;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
