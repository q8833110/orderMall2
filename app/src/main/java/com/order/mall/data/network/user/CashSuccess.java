package com.order.mall.data.network.user;

public class CashSuccess {

    /**
     * id : 577197004422119424
     * userId : 500000
     * rmbValue : 90
     * encashValue : 1000
     * encashValueRate : 10
     * serviceValue : 100
     * serviceValueRate : 10
     * createDate : 2019-05-12T18:14:49.657+0800
     * reciveWay : 0
     * accountName : 123
     * accountNo : 123
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
