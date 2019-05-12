package com.order.mall.data.network.user;

import java.util.List;

public class BounsScoreList {

    /**
     * total : 2
     * size : 10
     * pages : 1
     * pageNum : 1
     * data : [{"id":"575060515819094016","userId":500000,"userSourceId":null,"bonusValue":100,"bonusType":1,"createDate":"2019-05-06 20:45:14","balanceAfterOperate":23,"remark":null}]
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
         * id : 575060515819094016
         * userId : 500000
         * userSourceId : null
         * bonusValue : 100
         * bonusType : 1
         * createDate : 2019-05-06 20:45:14
         * balanceAfterOperate : 23
         * remark : null
         */

        private String id;
        private int userId;
        private Object userSourceId;
        private int bonusValue;
        private int bonusType;
        private String createDate;
        private int balanceAfterOperate;
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

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }
    }
}
