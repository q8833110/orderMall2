package com.order.mall.data.network.financial;

import lombok.Data;

@Data
public class PreyPay {
        /**
         * bonusBalance : 0
         * cashBalance : 0
         * consumeBalance : 0
         * id : 0
         * tradeBalance : 0
         * tradeScore : 0
         * userId : 0
         */

        private float bonusBalance;
        private float cashBalance;
        private float consumeBalance;
        private int id;
        private float tradeBalance;
        private float tradeScore;
        private int userId;

        public float getBonusBalance() {
                return bonusBalance;
        }

        public void setBonusBalance(float bonusBalance) {
                this.bonusBalance = bonusBalance;
        }

        public float getCashBalance() {
                return cashBalance;
        }

        public void setCashBalance(float cashBalance) {
                this.cashBalance = cashBalance;
        }

        public float getConsumeBalance() {
                return consumeBalance;
        }

        public void setConsumeBalance(float consumeBalance) {
                this.consumeBalance = consumeBalance;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public float getTradeBalance() {
                return tradeBalance;
        }

        public void setTradeBalance(float tradeBalance) {
                this.tradeBalance = tradeBalance;
        }

        public float getTradeScore() {
                return tradeScore;
        }

        public void setTradeScore(float tradeScore) {
                this.tradeScore = tradeScore;
        }

        public int getUserId() {
                return userId;
        }

        public void setUserId(int userId) {
                this.userId = userId;
        }
}
