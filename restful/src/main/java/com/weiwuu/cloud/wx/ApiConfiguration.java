package com.weiwuu.cloud.wx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weiwuu.cloud.wx.factory.ApiFactory;
import com.weiwuu.cloud.wx.factory.RedisServiceFactory;
import com.weiwuu.cloud.wx.factory.VerifyFactory;
import com.weiwuu.cloud.wx.factory.WXConfigFactory;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ApiConfiguration extends Configuration
{
    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    @Valid
    @NotNull
    @JsonProperty
    private ApiFactory apiFactory = new ApiFactory();

    @Valid
    @NotNull
    @JsonProperty
    private VerifyFactory verifyFactory = new VerifyFactory();

    @Valid
    @NotNull
    @JsonProperty
    private WXConfigFactory wxConfigFactory = new WXConfigFactory();

    @Valid
    @NotNull
    private RedisServiceFactory redisServiceFactory = new RedisServiceFactory();

    //@JsonProperty("database")
    public DataSourceFactory getDataSourceFactory()
    {
        return database;
    }

    @JsonProperty("api")
    public ApiFactory getApiFactory()
    {
        return apiFactory;
    }

    @JsonProperty("verify")
    public VerifyFactory getVerifyFactory()
    {
        return verifyFactory;
    }

    @JsonProperty("wxconfig")
    public WXConfigFactory getWxConfigFactory()
    {
        return wxConfigFactory;
    }

    @JsonProperty("redisService")
    public RedisServiceFactory getRedisServiceFactory() {
        return redisServiceFactory;
    }

}
