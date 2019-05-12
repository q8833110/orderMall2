package com.order.mall.data.network.user;

public class AlipayList {

    /**
     * id : 575010337867169792
     * userId : 500000
     * aliPayAccount : 2757729569@qq.com
     * accountRealName : 周良才
     * openOrNot : false
     */

    private String id;
    private int userId;
    private String aliPayAccount;
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

    public String getAliPayAccount() {
        return aliPayAccount;
    }

    public void setAliPayAccount(String aliPayAccount) {
        this.aliPayAccount = aliPayAccount;
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
