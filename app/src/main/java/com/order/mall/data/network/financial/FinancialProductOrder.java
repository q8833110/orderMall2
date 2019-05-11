package com.order.mall.data.network.financial;

import java.util.List;

import lombok.Data;

@Data
public class FinancialProductOrder {
    /**
     * annualizedRate : 0
     * consumeRate : 0
     * createTime : 2019-05-11T12:18:51.447Z
     * days : 0
     * endTimeMs : 0
     * finishTime : 2019-05-11T12:18:51.447Z
     * id : string
     * images : string
     * imagess : ["string"]
     * interest : 0
     * onsaleTime : 2019-05-11T12:18:51.448Z
     * status : 0
     * title : string
     * tradeScore : 0
     * userId : 0
     */

    private int annualizedRate;
    private int consumeRate;
    private String createTime;
    private int days;
    private int endTimeMs;
    private String finishTime;
    private String id;
    private String images;
    private int interest;
    private String onsaleTime;
    private int status;
    private String title;
    private int tradeScore;
    private int userId;
    private List<String> imagess;

    public int getAnnualizedRate() {
        return annualizedRate;
    }

    public void setAnnualizedRate(int annualizedRate) {
        this.annualizedRate = annualizedRate;
    }

    public int getConsumeRate() {
        return consumeRate;
    }

    public void setConsumeRate(int consumeRate) {
        this.consumeRate = consumeRate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getInterest() {
        return interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public String getOnsaleTime() {
        return onsaleTime;
    }

    public void setOnsaleTime(String onsaleTime) {
        this.onsaleTime = onsaleTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTradeScore() {
        return tradeScore;
    }

    public void setTradeScore(int tradeScore) {
        this.tradeScore = tradeScore;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<String> getImagess() {
        return imagess;
    }

    public void setImagess(List<String> imagess) {
        this.imagess = imagess;
    }
}
