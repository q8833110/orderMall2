package com.order.mall.data.network.financial;

import java.util.List;

import lombok.Data;

@Data
public class FinancialProductDetail {
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
    }
