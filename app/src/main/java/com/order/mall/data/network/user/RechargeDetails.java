package com.order.mall.data.network.user;

public class RechargeDetails {

    /**
     * id : 575643105517109248
     * userId : 500000
     * amount : 300
     * tradeScore : 4200
     * createTime : 2019-05-08 11:20:11
     * finishTime : 2019-05-08T11:22:02.000+0800
     * payWayValue : 1
     * payWayName : 微信支付
     * toAccount : 收款方微信昵称
     * toUser : 收款方真实名称
     * toQrCode : http://tcmall.oss-cn-shenzhen.aliyuncs.com/imgs/201905072348090456256675.jpg
     * proofOfPay : http://tcmall.oss-cn-shenzhen.aliyuncs.com/imgs/201905081120587080311096.jpg
     * proofOfPays : null
     * status : 2
     */

    private String id;
    private int userId;
    private double amount;
    private double tradeScore;
    private String createTime;
    private String finishTime;
    private int payWayValue;
    private String payWayName;
    private String toAccount;
    private String toUser;
    private String toQrCode;
    private String proofOfPay;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
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

    public String getProofOfPay() {
        return proofOfPay;
    }

    public void setProofOfPay(String proofOfPay) {
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
