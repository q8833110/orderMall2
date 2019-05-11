package com.order.mall.data.network.user;

public class UserData {
    /**
     * account : string
     * avatar : string
     * bonusBalance : 0
     * cashBalance : 0
     * consumeBalance : 0
     * tradeBalance : 0
     * userId : 0
     */

    private String account;
    private String avatar;
    private int bonusBalance;
    private int cashBalance;
    private int consumeBalance;
    private int tradeBalance;
    private int userId;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getBonusBalance() {
        return bonusBalance;
    }

    public void setBonusBalance(int bonusBalance) {
        this.bonusBalance = bonusBalance;
    }

    public int getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(int cashBalance) {
        this.cashBalance = cashBalance;
    }

    public int getConsumeBalance() {
        return consumeBalance;
    }

    public void setConsumeBalance(int consumeBalance) {
        this.consumeBalance = consumeBalance;
    }

    public int getTradeBalance() {
        return tradeBalance;
    }

    public void setTradeBalance(int tradeBalance) {
        this.tradeBalance = tradeBalance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
