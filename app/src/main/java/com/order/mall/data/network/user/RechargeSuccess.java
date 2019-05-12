package com.order.mall.data.network.user;

public class RechargeSuccess {

    /**
     * id : 577146085433147392
     * userId : 500000
     * amount : 1
     * tradeScore : 1.5
     * createTime : 2019-05-12 14:52:29
     * finishTime : null
     * payWayValue : 1
     * payWayName : 微信支付
     * toAccount : 收款方微信昵称
     * toUser : 收款方真实名称
     * toQrCode : http://tcmall.oss-cn-shenzhen.aliyuncs.com/imgs/201905072348090456256675.jpg
     * proofOfPay : null
     * proofOfPays : null
     * status : 0
     */

    private String id;
    private int userId;
    private int amount;
    private double tradeScore;
    private String createTime;
    private Object finishTime;
    private int payWayValue;
    private String payWayName;
    private String toAccount;
    private String toUser;
    private String toQrCode;
    private Object proofOfPay;
    private Object proofOfPays;
    private int status;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTradeScore() {
        return tradeScore;
    }

    public void setTradeScore(double tradeScore) {
        this.tradeScore = tradeScore;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Object finishTime) {
        this.finishTime = finishTime;
    }

    public int getPayWayValue() {
        return payWayValue;
    }

    public void setPayWayValue(int payWayValue) {
        this.payWayValue = payWayValue;
    }

    public String getPayWayName() {
        return payWayName;
    }

    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getToQrCode() {
        return toQrCode;
    }

    public void setToQrCode(String toQrCode) {
        this.toQrCode = toQrCode;
    }

    public Object getProofOfPay() {
        return proofOfPay;
    }

    public void setProofOfPay(Object proofOfPay) {
        this.proofOfPay = proofOfPay;
    }

    public Object getProofOfPays() {
        return proofOfPays;
    }

    public void setProofOfPays(Object proofOfPays) {
        this.proofOfPays = proofOfPays;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
