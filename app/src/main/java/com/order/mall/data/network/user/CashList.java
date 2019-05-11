package com.order.mall.data.network.user;

import java.util.List;

public class CashList {

    /**
     * total : 3
     * size : 10
     * pages : 1
     * pageNum : 1
     * data : [{"id":"575059039315034112","userId":500000,"userSourceId":null,"tradeAccount":null,"cashValue":2000,"serviceValue":0,"cashType":1,"createDate":"2019-05-06 20:39:19","balanceAfterOperate":345,"remark":null,"completeDate":"2019-05-06 20:39:22"}]
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
         * id : 575059039315034112
         * userId : 500000
         * userSourceId : null
         * tradeAccount : null
         * cashValue : 2000
         * serviceValue : 0
         * cashType : 1
         * createDate : 2019-05-06 20:39:19
         * balanceAfterOperate : 345
         * remark : null
         * completeDate : 2019-05-06 20:39:22
         */

        private String id;
        private int userId;
        private Object userSourceId;
        private Object tradeAccount;
        private int cashValue;
        private int serviceValue;
        private int cashType;
        private String createDate;
        private int balanceAfterOperate;
        private Object remark;
        private String completeDate;

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

        public Object getTradeAccount() {
            return tradeAccount;
        }

        public void setTradeAccount(Object tradeAccount) {
            this.tradeAccount = tradeAccount;
        }

        public int getCashValue() {
            return cashValue;
        }

        public void setCashValue(int cashValue) {
            this.cashValue = cashValue;
        }

        public int getServiceValue() {
            return serviceValue;
        }

        public void setServiceValue(int serviceValue) {
            this.serviceValue = serviceValue;
        }

        public int getCashType() {
            return cashType;
        }

        public void setCashType(int cashType) {
            this.cashType = cashType;
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

        public String getCompleteDate() {
            return completeDate;
        }

        public void setCompleteDate(String completeDate) {
            this.completeDate = completeDate;
        }
    }
}
