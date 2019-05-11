package com.order.mall.data.network.user;

public class TradeDetails {


    /**
     * id : 1
     * userId : 500008
     * tradeValue : 1500
     * tradeType : 0
     * createDate : 2019-05-05 15:10:44
     * balanceAfterOperate : 2000
     * remark :
     */

    private String id;
    private int userId;
    private int tradeValue;
    private int tradeType;
    private String createDate;
    private int balanceAfterOperate;
    private String remark;

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

    public int getTradeValue() {
        return tradeValue;
    }

    public void setTradeValue(int tradeValue) {
        this.tradeValue = tradeValue;
    }

    public int getTradeType() {
        return tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getBalanceAfterOperate() {
        return balanceAfterOperate;
    }

    public void setBalanceAfterOperate(int balanceAfterOperate) {
        this.balanceAfterOperate = balanceAfterOperate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
