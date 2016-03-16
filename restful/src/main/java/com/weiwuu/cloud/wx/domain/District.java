package com.weiwuu.cloud.wx.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hui on 2015/11/5.
 */
public class District
{
    private int districtId;
    private int ciytId;
    private String name;
    private String abbreviation;
    private int displayOrder;

    @JsonProperty
    public int getDistrictId()
    {
        return this.districtId;
    }
    @JsonProperty
    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }
    @JsonProperty
    public int getCiytId() {
        return this.ciytId;
    }
    @JsonProperty
    public void setCiytId(int ciytId) {
        this.ciytId = ciytId;
    }
    @JsonProperty
    public String getName() {
        return this.name;
    }
    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty
    public String getAbbreviation() {
        return this.abbreviation;
    }
    @JsonProperty
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
    @JsonProperty
    public int getDisplayOrder() {
        return this.displayOrder;
    }
    @JsonProperty
    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public District(int districtId, int ciytId, String name, String abbreviation, int displayOrder)
    {
        this.districtId = districtId;
        this.ciytId = ciytId;
        this.name = name;
        this.abbreviation = abbreviation;
        this.displayOrder = displayOrder;
    }

    public District()
    {
    }
}
