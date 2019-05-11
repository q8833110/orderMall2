package com.order.mall.data.network.user;

import java.util.List;

public class TradeBalanceList {


    /**
     * data : [{"balanceAfterOperate":0,"createDate":"2019-05-11T04:46:34.128Z","id":"string","remark":"string","tradeType":0,"tradeValue":0,"userId":0}]
     * pageNum : 0
     * pages : 0
     * size : 0
     * total : 0
     */

    private int pageNum;
    private int pages;
    private int size;
    private int total;
    private List<DataBean> data;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * balanceAfterOperate : 0
         * createDate : 2019-05-11T04:46:34.128Z
         * id : string
         * remark : string
         * tradeType : 0
         * tradeValue : 0
         * userId : 0
         */

        private int balanceAfterOperate;
        private String createDate;
        private String id;
        private String remark;
        private int tradeType;
        private int tradeValue;
        private int userId;

        public int getBalanceAfterOperate() {
            return balanceAfterOperate;
        }

        public void setBalanceAfterOperate(int balanceAfterOperate) {
            this.balanceAfterOperate = balanceAfterOperate;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getTradeType() {
            return tradeType;
        }

        public void setTradeType(int tradeType) {
            this.tradeType = tradeType;
        }

        public int getTradeValue() {
            return tradeValue;
        }

        public void setTradeValue(int tradeValue) {
            this.tradeValue = tradeValue;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
