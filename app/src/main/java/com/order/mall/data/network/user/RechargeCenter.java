package com.order.mall.data.network.user;

import java.util.List;

public class RechargeCenter {


    /**
     * total : 2
     * size : 10
     * pages : 1
     * pageNum : 1
     * data : [{"id":"575643105517109248","userId":500000,"amount":300,"tradeScore":4200,"createTime":"2019-05-08 11:20:11","finishTime":"2019-05-08T11:22:02.000+0800","payWayValue":1,"payWayName":"微信支付","toAccount":"收款方微信昵称","toUser":"收款方真实名称","toQrCode":"http://tcmall.oss-cn-shenzhen.aliyuncs.com/imgs/201905072348090456256675.jpg","proofOfPay":"http://tcmall.oss-cn-shenzhen.aliyuncs.com/imgs/201905081120587080311096.jpg","proofOfPays":null,"status":2},{"id":"574999379513966592","userId":500000,"amount":1000,"tradeScore":1500,"createTime":"2019-05-06 16:42:15","finishTime":"2019-05-06T22:29:01.000+0800","payWayValue":2,"payWayName":"支付宝支付","toAccount":"86869861@qq.com","toUser":"郑涛","toQrCode":"http://tcmall.oss-cn-shenzhen.aliyuncs.com/imgs/201905051845528016232574.jpg","proofOfPay":"http://tcmall.oss-cn-shenzhen.aliyuncs.com/imgs/201905061642304825807503.jpg;http://tcmall.oss-cn-shenzhen.aliyuncs.com/imgs/201905061642495344013820.jpg","proofOfPays":null,"status":2}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
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
        private int amount;
        private int tradeScore;
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

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getTradeScore() {
            return tradeScore;
        }

        public void setTradeScore(int tradeScore) {
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
}
