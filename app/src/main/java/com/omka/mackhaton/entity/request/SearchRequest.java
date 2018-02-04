package com.omka.mackhaton.entity.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchRequest {
    public SearchRequest() {
    }

    public SearchRequest(String startDate, String endDate, int minCount, int maxCount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("minCount")
    @Expose
    private int minCount;
    @SerializedName("maxCount")
    @Expose
    private int maxCount;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getMinCount() {
        return minCount;
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

}