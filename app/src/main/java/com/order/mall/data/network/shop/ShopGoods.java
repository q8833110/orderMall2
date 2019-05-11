package com.order.mall.data.network.shop;

import java.util.List;

public class ShopGoods {
    /**
     * data : [{"details":"string","freight":0,"id":0,"name":"string","picture":"string","price":0,"stock":0,"synopsis":"string"}]
     * pageNum : 0
     * pages : 0
     * size : 0
     * total : 0
     */

    private int pageNum;
    private int pages;
    private int size;
    private int total;
    private List<ShopGood> data;

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

    public List<ShopGood> getData() {
        return data;
    }

    public void setData(List<ShopGood> data) {
        this.data = data;
    }

    public static class ShopGood {
        /**
         * details : string
         * freight : 0
         * id : 0
         * name : string
         * picture : string
         * price : 0
         * stock : 0
         * synopsis : string
         */

        private String details;
        private int freight;
        private int id;
        private String name;
        private String picture;
        private int price;
        private int stock;
        private String synopsis;

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public int getFreight() {
            return freight;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }
    }
}
