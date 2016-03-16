package com.weiwuu.cloud.wx.factory;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by hui on 2015/11/5.
 */
public class ApiFactory
{
    @NotNull
    private String host;
    @NotNull
    private String imhost;
    @JsonProperty
    public String getHost()
    {
        return host;
    }
    @JsonProperty
    public void setHost(String host)
    {
        this.host = host;
    }
    @JsonProperty
    public String getImhost()
    {
        return imhost;
    }
    @JsonProperty
    public void setImhost(String imhost)
    {
        this.imhost = imhost;
    }
}
