package com.weiwuu.cloud.wx.factory;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by hui on 2015/11/5.
 */
public class WXConfigFactory
{
    @NotNull
    private String appID;
    @NotNull
    private String appSecret;
    @NotNull
    private String token;
    @NotNull
    private String encodingAESKey;
    @NotNull
    private String appID2;
    @NotNull
    private String appSecret2;
    @JsonProperty
    public String getAppID2()
    {
        return appID2;
    }
    @JsonProperty
    public void setAppID2(String appID2)
    {
        this.appID2 = appID2;
    }
    @JsonProperty
    public String getAppSecret2()
    {
        return appSecret2;
    }
    @JsonProperty
    public void setAppSecret2(String appSecret2)
    {
        this.appSecret2 = appSecret2;
    }
    @JsonProperty
    public String getAppID()
    {
        return appID;
    }
    @JsonProperty
    public void setAppID(String appID)
    {
        this.appID = appID;
    }
    @JsonProperty
    public String getAppSecret()
    {
        return appSecret;
    }
    @JsonProperty
    public void setAppSecret(String appSecret)
    {
        this.appSecret = appSecret;
    }
    @JsonProperty
    public String getToken()
    {
        return token;
    }
    @JsonProperty
    public void setToken(String token)
    {
        this.token = token;
    }
    @JsonProperty
    public String getEncodingAESKey()
    {
        return encodingAESKey;
    }
    @JsonProperty
    public void setEncodingAESKey(String encodingAESKey)
    {
        this.encodingAESKey = encodingAESKey;
    }
}
