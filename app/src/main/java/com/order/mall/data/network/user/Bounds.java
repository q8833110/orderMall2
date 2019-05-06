package com.order.mall.data.network.user;

import java.util.List;

/**
 * 奖金积分列表
 */
public class Bounds {

    /**
     * data : [{"balanceAfterOperate":0,"bonusType":0,"bonusValue":0,"createDate":"2019-05-06T14:23:57.329Z","id":"string","remark":"string","userId":0}]
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
         * bonusType : 0
         * bonusValue : 0
         * createDate : 2019-05-06T14:23:57.329Z
         * id : string
         * remark : string
         * userId : 0
         */

        private int balanceAfterOperate;
        private int bonusType;
        private int bonusValue;
        private String createDate;
        private String id;
        private String remark;
        private int userId;

        public int getBalanceAfterOperate() {
            return balanceAfterOperate;
        }

        public void setBalanceAfterOperate(int balanceAfterOperate) {
            this.balanceAfterOperate = balanceAfterOperate;
        }

        public int getBonusType() {
            return bonusType;
        }

        public void setBonusType(int bonusType) {
            this.bonusType = bonusType;
        }

        public int getBonusValue() {
            return bonusValue;
        }

        public void setBonusValue(int bonusValue) {
            this.bonusValue = bonusValue;
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
