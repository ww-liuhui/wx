package com.weiwuu.cloud.wx.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hui on 2015/11/5.
 */
public class GardenMark
{
    private int id;
    private String markName;

    public GardenMark()
    {
    }

    public GardenMark(int id, String markName)
    {
        this.id = id;
        this.markName = markName;
    }

    @JsonProperty("Id")
    public int getId() {
        return this.id;
    }

    @JsonProperty("Id")
    public void setId(int id) {
        this.id = id;
    }

    public String getMarkName() {
        return this.markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }
}
