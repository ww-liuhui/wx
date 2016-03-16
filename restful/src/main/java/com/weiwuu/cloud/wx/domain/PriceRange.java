package com.weiwuu.cloud.wx.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hui on 2015/11/5.
 */
public class PriceRange
{
    private int id;
    private String code;
    private int cityId;
    private int minPrice;
    private int maxPrice;

    @JsonProperty("Id")
    public int getId()
    {
        return this.id;
    }
    @JsonProperty("Id")
    public void setId(int id) {
        this.id = id;
    }
    @JsonProperty("Code")
    public String getCode() {
        return this.code;
    }
    @JsonProperty("Code")
    public void setCode(String code) {
        this.code = code;
    }
    @JsonProperty("CityId")
    public int getCityId() {
        return this.cityId;
    }
    @JsonProperty("CityId")
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
    @JsonProperty("MinPrice")
    public int getMinPrice() {
        return this.minPrice;
    }
    @JsonProperty("MinPrice")
    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }
    @JsonProperty("MaxPrice")
    public int getMaxPrice() {
        return this.maxPrice;
    }
    @JsonProperty("MaxPrice")
    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public PriceRange(int id, String code, int cityId, int minPrice, int maxPrice)
    {
        this.id = id;
        this.code = code;
        this.cityId = cityId;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public PriceRange()
    {
    }
}
