package com.weiwuu.cloud.wx.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;

@JsonSerialize(include= Inclusion.NON_NULL)
public class CreateGroupRequest
{
    @NotNull
    private String bcode;
    @NotNull
    private String mcode;
    @NotNull
    private String gid;
    @NotNull
    private String gname;
    @DefaultValue("")
    private String pic;

    public CreateGroupRequest()
    {
    }
    public CreateGroupRequest(String bcode, String mcode, String gid, String gname)
    {
        this.bcode = bcode;
        this.mcode = mcode;
        this.gid = gid;
        this.gname = gname;
    }

    public CreateGroupRequest(String bcode, String mcode, String gid, String gname, String pic)
    {
        this.bcode = bcode;
        this.mcode = mcode;
        this.gid = gid;
        this.gname = gname;
        this.pic = pic;
    }

    @JsonProperty
    public String getPic()
    {
        return pic;
    }
    @JsonProperty
    public void setPic(String pic)
    {
        this.pic = pic;
    }

    @JsonProperty
    public String getBcode()
    {
        return bcode;
    }
    @JsonProperty
    public void setBcode(String bcode)
    {
        this.bcode = bcode;
    }
    @JsonProperty
    public String getMcode()
    {
        return mcode;
    }
    @JsonProperty
    public void setMcode(String mcode)
    {
        this.mcode = mcode;
    }
    @JsonProperty
    public String getGid()
    {
        return gid;
    }
    @JsonProperty
    public void setGid(String gid)
    {
        this.gid = gid;
    }
    @JsonProperty
    public String getGname()
    {
        return gname;
    }
    @JsonProperty
    public void setGname(String gname)
    {
        this.gname = gname;
    }
}