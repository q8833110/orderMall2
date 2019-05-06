package com.order.mall.data.network.user;

import android.os.Parcel;
import android.os.Parcelable;

public class UserDeliverAddress  implements Parcelable {
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

    protected UserDeliverAddress(Parcel in) {
        address = in.readString();
        addressDetail = in.readString();
        id = in.readString();
        isDefault = in.readByte() != 0;
        mobile = in.readString();
        reciver = in.readString();
        userId = in.readInt();
    }

    public static final Creator<UserDeliverAddress> CREATOR = new Creator<UserDeliverAddress>() {
        @Override
        public UserDeliverAddress createFromParcel(Parcel in) {
            return new UserDeliverAddress(in);
        }

        @Override
        public UserDeliverAddress[] newArray(int size) {
            return new UserDeliverAddress[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeString(addressDetail);
        dest.writeString(id);
        dest.writeByte((byte) (isDefault ? 1 : 0));
        dest.writeString(mobile);
        dest.writeString(reciver);
        dest.writeInt(userId);
    }
}
