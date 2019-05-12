package com.order.mall.data.network.user;

public class BonusScoreDetails {

    /**
     * id : 1
     * userId : 500008
     * userSourceId : null
     * bonusValue : 1500
     * bonusType : 0
     * createDate : 2019-05-05 15:10:44
     * balanceAfterOperate : 2000
     * remark : null
     */

    private String id;
    private int userId;
    private Object userSourceId;
    private int bonusValue;
    private int bonusType;
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

    public int getBonusValue() {
        return bonusValue;
    }

    public void setBonusValue(int bonusValue) {
        this.bonusValue = bonusValue;
    }

    public int getBonusType() {
        return bonusType;
    }

    public void setBonusType(int bonusType) {
        this.bonusType = bonusType;
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
