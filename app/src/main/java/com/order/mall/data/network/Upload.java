package com.order.mall.data.network;

import java.util.List;

public class Upload {

    /**
     * message : success
     * code : 0
     * data : ["http://tcmall.oss-cn-shenzhen.aliyuncs.com/imgs/201905121056362203777701.jpg"]
     */

    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
