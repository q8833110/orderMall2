package com.order.mall.data.network.user;

import java.util.List;

public class TradeOrderList {

    /**
     * total : 35
     * size : 10
     * pages : 4
     * pageNum : 1
     * data : [{"id":"575657431124672512","userId":500000,"title":"杜蕾斯","images":"没有图片","imagess":null,"tradeScore":21.34,"annualizedRate":0.1235,"consumeRate":0.0234,"days":7,"status":1,"createTime":"2019-05-08 12:17:07","endTimeMs":333307898,"onsaleTime":null,"finishTime":null,"interest":2.63}]
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
         * id : 575657431124672512
         * userId : 500000
         * title : 杜蕾斯
         * images : 没有图片
         * imagess : null
         * tradeScore : 21.34
         * annualizedRate : 0.1235
         * consumeRate : 0.0234
         * days : 7
         * status : 1
         * createTime : 2019-05-08 12:17:07
         * endTimeMs : 333307898
         * onsaleTime : null
         * finishTime : null
         * interest : 2.63
         */

        private String id;
        private int userId;
        private String title;
        private String images;
        private Object imagess;
        private double tradeScore;
        private double annualizedRate;
        private double consumeRate;
        private int days;
        private int status;
        private String createTime;
        private int endTimeMs;
        private Object onsaleTime;
        private Object finishTime;
        private double interest;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public Object getImagess() {
            return imagess;
        }

        public void setImagess(Object imagess) {
            this.imagess = imagess;
        }

        public double getTradeScore() {
            return tradeScore;
        }

        public void setTradeScore(double tradeScore) {
            this.tradeScore = tradeScore;
        }

        public double getAnnualizedRate() {
            return annualizedRate;
        }

        public void setAnnualizedRate(double annualizedRate) {
            this.annualizedRate = annualizedRate;
        }

        public double getConsumeRate() {
            return consumeRate;
        }

        public void setConsumeRate(double consumeRate) {
            this.consumeRate = consumeRate;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getEndTimeMs() {
            return endTimeMs;
        }

        public void setEndTimeMs(int endTimeMs) {
            this.endTimeMs = endTimeMs;
        }

        public Object getOnsaleTime() {
            return onsaleTime;
        }

        public void setOnsaleTime(Object onsaleTime) {
            this.onsaleTime = onsaleTime;
        }

        public Object getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(Object finishTime) {
            this.finishTime = finishTime;
        }

        public double getInterest() {
            return interest;
        }

        public void setInterest(double interest) {
            this.interest = interest;
        }
    }
}
