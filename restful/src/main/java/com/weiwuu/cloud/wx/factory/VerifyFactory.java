package com.weiwuu.cloud.wx.factory;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by hui on 2015/11/6.
 */
public class VerifyFactory
{
    @NotEmpty
    private String name;
    @NotEmpty
    private String expires_in;

    @JsonProperty
    public String getName()
    {
        return name;
    }
    @JsonProperty
    public void setName(String name)
    {
        this.name = name;
    }
    @JsonProperty
    public String getExpires_in()
    {
        return expires_in;
    }
    @JsonProperty
    public void setExpires_in(String expires_in)
    {
        this.expires_in = expires_in;
    }
}
