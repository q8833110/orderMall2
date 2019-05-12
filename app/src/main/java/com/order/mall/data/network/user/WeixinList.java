package com.order.mall.data.network.user;

public class WeixinList {

    /**
     * id : 577219563356160000
     * userId : 500000
     * weixinPayAccount : 123456
     * accountRealName : cyq
     * openOrNot : false
     */

    private String id;
    private int userId;
    private String weixinPayAccount;
    private String accountRealName;
    private boolean openOrNot;
    private boolean isCheck=false;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

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

    public String getWeixinPayAccount() {
        return weixinPayAccount;
    }

    public void setWeixinPayAccount(String weixinPayAccount) {
        this.weixinPayAccount = weixinPayAccount;
    }

    public String getAccountRealName() {
        return accountRealName;
    }

    public void setAccountRealName(String accountRealName) {
        this.accountRealName = accountRealName;
    }

    public boolean isOpenOrNot() {
        return openOrNot;
    }

    public void setOpenOrNot(boolean openOrNot) {
        this.openOrNot = openOrNot;
    }
}
