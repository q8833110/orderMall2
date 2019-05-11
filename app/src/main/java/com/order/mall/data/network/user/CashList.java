package com.order.mall.data.network.user;

import java.util.List;

public class CashList {

    /**
     * total : 1
     * size : 10
     * pages : 1
     * pageNum : 1
     * data : [{"id":"575653466861469696","userId":500000,"rmbValue":900,"encashValue":10000,"encashValueRate":10,"serviceValue":1000,"serviceValueRate":10,"createDate":"2019-05-08T12:01:22.000+0800","reciveWay":0,"accountName":"accountRealName","accountNo":"aliPayAccount","encashStatus":0,"remark":null}]
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
         * id : 575653466861469696
         * userId : 500000
         * rmbValue : 900
         * encashValue : 10000
         * encashValueRate : 10
         * serviceValue : 1000
         * serviceValueRate : 10
         * createDate : 2019-05-08T12:01:22.000+0800
         * reciveWay : 0
         * accountName : accountRealName
         * accountNo : aliPayAccount
         * encashStatus : 0
         * remark : null
         */

        private String id;
        private int userId;
        private int rmbValue;
        private int encashValue;
        private int encashValueRate;
        private int serviceValue;
        private int serviceValueRate;
        private String createDate;
        private int reciveWay;
        private String accountName;
        private String accountNo;
        private int encashStatus;
        private Object remark;

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

        public int getRmbValue() {
            return rmbValue;
        }

        public void setRmbValue(int rmbValue) {
            this.rmbValue = rmbValue;
        }

        public int getEncashValue() {
            return encashValue;
        }

        public void setEncashValue(int encashValue) {
            this.encashValue = encashValue;
        }

        public int getEncashValueRate() {
            return encashValueRate;
        }

        public void setEncashValueRate(int encashValueRate) {
            this.encashValueRate = encashValueRate;
        }

        public int getServiceValue() {
            return serviceValue;
        }

        public void setServiceValue(int serviceValue) {
            this.serviceValue = serviceValue;
        }

        public int getServiceValueRate() {
            return serviceValueRate;
        }

        public void setServiceValueRate(int serviceValueRate) {
            this.serviceValueRate = serviceValueRate;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getReciveWay() {
            return reciveWay;
        }

        public void setReciveWay(int reciveWay) {
            this.reciveWay = reciveWay;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(String accountNo) {
            this.accountNo = accountNo;
        }

        public int getEncashStatus() {
            return encashStatus;
        }

        public void setEncashStatus(int encashStatus) {
            this.encashStatus = encashStatus;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }
    }
}
