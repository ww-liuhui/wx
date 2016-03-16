package com.weiwuu.cloud.wx.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hui on 2015/11/5.
 */
public class HouseType
{
    private int id;
    private String code;
    private String name;

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
    @JsonProperty("Name")
    public String getName() {
        return this.name;
    }
    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    public HouseType(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public HouseType()
    {
    }
}
