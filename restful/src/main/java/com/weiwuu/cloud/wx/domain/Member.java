package com.weiwuu.cloud.wx.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Member
{
    private int id;
    private int type_id;
    private String code;
    private String name;
    private String avatarUrl;
    private String qq;
    private String notes;
    private String email;
    private String mobile;
    private String weixin;
    private String password;
    private Date createTime;
    private Date updateTime;
    private String type_name;
    private int city_id;

    public int getCity_id()
    {
        return city_id;
    }

    public void setCity_id(int city_id)
    {
        this.city_id = city_id;
    }

    @JsonProperty
    public String getType_name()
    {
        return type_name;
    }
    @JsonProperty
    public void setType_name(String type_name)
    {
        this.type_name = type_name;
    }

    @JsonProperty("Id")
    public int getId()
    {
        return id;
    }

    @JsonProperty("Id")
    public void setId(int id)
    {
        this.id = id;
    }

    @JsonProperty
    public int getType_id()
    {
        return type_id;
    }

    @JsonProperty
    public void setType_id(int type_id)
    {
        this.type_id = type_id;
    }

    @JsonProperty
    public String getCode()
    {
        return code;
    }

    @JsonProperty
    public void setCode(String code)
    {
        this.code = code;
    }

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
    public String getAvatarUrl()
    {
        return avatarUrl;
    }

    @JsonProperty
    public void setAvatarUrl(String avatarUrl)
    {
        this.avatarUrl = avatarUrl;
    }

    @JsonProperty
    public String getQq()
    {
        return qq;
    }

    @JsonProperty
    public void setQq(String qq)
    {
        this.qq = qq;
    }

    @JsonProperty
    public String getNotes()
    {
        return notes;
    }

    @JsonProperty
    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    @JsonProperty
    public String getEmail()
    {
        return email;
    }

    @JsonProperty
    public void setEmail(String email)
    {
        this.email = email;
    }

    @JsonProperty
    public String getMobile()
    {
        return mobile;
    }

    @JsonProperty
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    @JsonProperty
    public String getWeixin()
    {
        return weixin;
    }

    @JsonProperty
    public void setWeixin(String weixin)
    {
        this.weixin = weixin;
    }

    @JsonProperty
    public String getPassword()
    {
        return password;
    }

    @JsonProperty
    public void setPassword(String password)
    {
        this.password = password;
    }

    @JsonProperty
    public Date getCreateTime()
    {
        return createTime;
    }

    @JsonProperty
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    @JsonProperty
    public Date getUpdateTime()
    {
        return updateTime;
    }

    @JsonProperty
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public Member(int id, int type_id, String code, String name, String avatarUrl, String qq, String notes, String email, String mobile, String weixin, String password, Date createTime, Date updateTime)
    {
        super();
        this.id = id;
        this.type_id = type_id;
        this.code = code;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.qq = qq;
        this.notes = notes;
        this.email = email;
        this.mobile = mobile;
        this.weixin = weixin;
        this.password = password;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Member(int id, int type_id, String code, String name, String avatarUrl, String mobile, String weixin)
    {
        this.id = id;
        this.type_id = type_id;
        this.code = code;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.mobile = mobile;
        this.weixin = weixin;
    }
    public Member(int id, int type_id, String code, String name, String avatarUrl, String mobile, String weixin,int
            city_id)
    {
        this.id = id;
        this.type_id = type_id;
        this.code = code;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.mobile = mobile;
        this.weixin = weixin;
        this.city_id = city_id;
    }
    public Member()
    {
        super();
    }

}
