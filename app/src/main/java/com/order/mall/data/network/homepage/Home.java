package com.order.mall.data.network.homepage;

import java.util.List;

public class Home {

        /**
         * banner : ["string"]
         * id : string
         * productScDetails : string
         * scoreScDetails : string
         */

        private String id;
        private String productScDetails;
        private String scoreScDetails;
        private List<String> banner;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductScDetails() {
            return productScDetails;
        }

        public void setProductScDetails(String productScDetails) {
            this.productScDetails = productScDetails;
        }

        public String getScoreScDetails() {
            return scoreScDetails;
        }

        public void setScoreScDetails(String scoreScDetails) {
            this.scoreScDetails = scoreScDetails;
        }

        public List<String> getBanner() {
            return banner;
        }

        public void setBanner(List<String> banner) {
            this.banner = banner;
        }
    }
