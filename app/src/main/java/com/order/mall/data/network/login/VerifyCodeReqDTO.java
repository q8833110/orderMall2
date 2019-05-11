package com.order.mall.data.network.login;

import lombok.Data;

@Data
public class VerifyCodeReqDTO {
    private Integer type;

    private String mobile;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
