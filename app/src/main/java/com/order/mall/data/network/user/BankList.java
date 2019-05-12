package com.order.mall.data.network.user;

public class BankList {

    /**
     * id : 575651420796092416
     * userId : 500000
     * accountName : 呼呼
     * bankName : 中国银行
     * subbranchName : 北京朝阳支行
     * bankNo : 6228123456000017
     * openOrNot : true
     */

    private String id;
    private int userId;
    private String accountName;
    private String bankName;
    private String subbranchName;
    private String bankNo;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSubbranchName() {
        return subbranchName;
    }

    public void setSubbranchName(String subbranchName) {
        this.subbranchName = subbranchName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public boolean isOpenOrNot() {
        return openOrNot;
    }

    public void setOpenOrNot(boolean openOrNot) {
        this.openOrNot = openOrNot;
    }
}
