package com.order.mall.data.network.user;

public class ConsumeDetails {

    /**
     * id : 1
     * userId : 500008
     * userSourceId : null
     * consumeValue : 1500
     * consumeType : 0
     * createDate : 2019-05-05 15:10:44
     * balanceAfterOperate : 2000
     * remark :
     */

    private String id;
    private int userId;
    private Object userSourceId;
    private int consumeValue;
    private int consumeType;
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

    public Object getUserSourceId() {
        return userSourceId;
    }

    public void setUserSourceId(Object userSourceId) {
        this.userSourceId = userSourceId;
    }

    public int getConsumeValue() {
        return consumeValue;
    }

    public void setConsumeValue(int consumeValue) {
        this.consumeValue = consumeValue;
    }

    public int getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(int consumeType) {
        this.consumeType = consumeType;
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
