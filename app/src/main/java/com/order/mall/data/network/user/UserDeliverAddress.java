package com.order.mall.data.network.user;

public class UserDeliverAddress {
        /**
         * address : string
         * addressDetail : string
         * id : string
         * isDefault : false
         * mobile : string
         * reciver : string
         * userId : 0
         */

        private String address;
        private String addressDetail;
        private String id;
        private boolean isDefault;
        private String mobile;
        private String reciver;
        private int userId;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsDefault() {
            return isDefault;
        }

        public void setIsDefault(boolean isDefault) {
            this.isDefault = isDefault;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getReciver() {
            return reciver;
        }

        public void setReciver(String reciver) {
            this.reciver = reciver;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
