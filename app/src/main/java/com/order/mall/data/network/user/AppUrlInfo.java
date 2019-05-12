package com.order.mall.data.network.user;

public class AppUrlInfo {
    /**
     * key :
     * value : -1111
     */

    private String key;
    private int value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static class QrCode{

        /**
         * url : http://v.wogjxm.com/systemconfig/list
         * qrcode : http://tcmall.oss-cn-shenzhen.aliyuncs.com/imgs/201905121259370675169243.blob
         */

        private String url;
        private String qrcode;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }
    }
}
