package com.order.mall.data.network.shop;

public class PrePay {

    /**
     * bonusBalance : 0
     * cashBalance : 0
     * consumeBalance : 0
     * consumeScore : 0
     * id : 0
     * tradeBalance : 0
     * userId : 0
     */

    private float bonusBalance;
    private float cashBalance;
    private float consumeBalance;
    private float consumeScore;
    private int id;
    private float tradeBalance;
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

    public float getConsumeScore() {
        return consumeScore;
    }

    public void setConsumeScore(float consumeScore) {
        this.consumeScore = consumeScore;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
