package com.order.mall.data.network.user;

import java.util.List;

public class ConsumeList {

    /**
     * total : 2
     * size : 10
     * pages : 1
     * pageNum : 1
     * data : [{"id":"575380031165956096","userId":500000,"userSourceId":null,"consumeValue":4794,"consumeType":1,"createDate":"2019-05-07 17:54:49","balanceAfterOperate":77,"remark":null},{"id":"575632637188636672","userId":500000,"userSourceId":null,"consumeValue":4794,"consumeType":1,"createDate":"2019-05-08 10:38:35","balanceAfterOperate":77251,"remark":null}]
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
         * id : 575380031165956096
         * userId : 500000
         * userSourceId : null
         * consumeValue : 4794
         * consumeType : 1
         * createDate : 2019-05-07 17:54:49
         * balanceAfterOperate : 77
         * remark : null
         */

        private String id;
        private int userId;
        private Object userSourceId;
        private int consumeValue;
        private int consumeType;
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

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }
    }
}
