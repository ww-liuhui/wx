package com.weiwuu.cloud.wx.entity.response;

/**
 * Created by hui on 2015/11/18.
 */
public class CityResponse
{
    private int cityId;
    private String cityName;
    private String cityNamePinyin;
    private int cityPid;

    public CityResponse()
    {
    }

    public CityResponse(int cityId, String cityName, String cityNamePinyin, int cityPid)
    {
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityNamePinyin = cityNamePinyin;
        this.cityPid = cityPid;
    }

    public int getCityId()
    {
        return cityId;
    }

    public void setCityId(int cityId)
    {
        this.cityId = cityId;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public String getCityNamePinyin()
    {
        return cityNamePinyin;
    }

    public void setCityNamePinyin(String cityNamePinyin)
    {
        this.cityNamePinyin = cityNamePinyin;
    }

    public int getCityPid()
    {
        return cityPid;
    }

    public void setCityPid(int cityPid)
    {
        this.cityPid = cityPid;
    }
}
