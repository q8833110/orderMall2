package com.order.mall.data.network.financial;

import java.util.List;

public class FinancialProduct {
    /**
     * data : [{"annualizedRate":0,"days":0,"endTimeMs":0,"id":0,"images":"string","imagess":["string"],"predictInterest":0,"title":"string","total":0,"tradeScore":0}]
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
         * annualizedRate : 0
         * days : 0
         * endTimeMs : 0
         * id : 0
         * images : string
         * imagess : ["string"]
         * predictInterest : 0
         * title : string
         * total : 0
         * tradeScore : 0
         */

        private int annualizedRate;
        private int days;
        private int endTimeMs;
        private int id;
        private String images;
        private int predictInterest;
        private String title;
        private int total;
        private int tradeScore;
        private List<String> imagess;

        public int getAnnualizedRate() {
            return annualizedRate;
        }

        public void setAnnualizedRate(int annualizedRate) {
            this.annualizedRate = annualizedRate;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public int getEndTimeMs() {
            return endTimeMs;
        }

        public void setEndTimeMs(int endTimeMs) {
            this.endTimeMs = endTimeMs;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public int getPredictInterest() {
            return predictInterest;
        }

        public void setPredictInterest(int predictInterest) {
            this.predictInterest = predictInterest;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTradeScore() {
            return tradeScore;
        }

        public void setTradeScore(int tradeScore) {
            this.tradeScore = tradeScore;
        }

        public List<String> getImagess() {
            return imagess;
        }

        public void setImagess(List<String> imagess) {
            this.imagess = imagess;
        }
    }
}
