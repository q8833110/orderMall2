package com.order.mall.data.network.financial;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FinancialProduct {
    /**
     * data : [{"annualizedRate":0,"days":0,"endTimeMs":0,"id":0,"images":"string","imagess":["string"],"predictInterest":0,"title":"string","total":0,"tradeScore":0}]
     * pageNum : 0
     * pages : 0
     * size : 0
     * total : 0
     */

    private int pageNum;
    private int pages;
    private int size;
    private int total;
    private List<DataBean> data;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean  implements Parcelable{
        /**
         * annualizedRate : 0
         * days : 0
         * endTimeMs : 0
         * id : 0
         * images : string
         * imagess : ["string"]
         * predictInterest : 0
         * title : string
         * total : 0
         * tradeScore : 0
         */

        private float annualizedRate;
        private int days;
        private int endTimeMs;
        private int id;
        private String images;
        private float predictInterest;
        private String title;
        private int total;
        private float tradeScore;
        private List<String> imagess;

        protected DataBean(Parcel in) {
            annualizedRate = in.readFloat();
            days = in.readInt();
            endTimeMs = in.readInt();
            id = in.readInt();
            images = in.readString();
            predictInterest = in.readFloat();
            title = in.readString();
            total = in.readInt();
            tradeScore = in.readFloat();
            imagess = in.createStringArrayList();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public float getAnnualizedRate() {
            return annualizedRate;
        }

        public void setAnnualizedRate(float annualizedRate) {
            this.annualizedRate = annualizedRate;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public int getEndTimeMs() {
            return endTimeMs;
        }

        public void setEndTimeMs(int endTimeMs) {
            this.endTimeMs = endTimeMs;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public float getPredictInterest() {
            return predictInterest;
        }

        public void setPredictInterest(float predictInterest) {
            this.predictInterest = predictInterest;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public float getTradeScore() {
            return tradeScore;
        }

        public void setTradeScore(float tradeScore) {
            this.tradeScore = tradeScore;
        }

        public List<String> getImagess() {
            return imagess;
        }

        public void setImagess(List<String> imagess) {
            this.imagess = imagess;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(annualizedRate);
            parcel.writeInt(days);
            parcel.writeInt(endTimeMs);
            parcel.writeInt(id);
            parcel.writeString(images);
            parcel.writeFloat(predictInterest);
            parcel.writeString(title);
            parcel.writeInt(total);
            parcel.writeFloat(tradeScore);
            parcel.writeStringList(imagess);
        }
    }
}
