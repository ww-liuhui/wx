package com.weiwuu.cloud.wx.domain.wx;

/***
 * 微信用户基本信息
 * 
 * @author hui
 *
 */
public class WeixinUserInfo
{
	// 用户的标识
	private String openId;
	// 关注状态
	private int subscribe;
	// 用户关注时间
	private String subscribeTime;
	// 昵称
	private String nickname;
	// 性别 1男，2女，0未知
	private int sex;
	// 所在国家
	private String country;
	// 所在省份
	private String province;
	// 所在城市
	private String city;
	// 用户的语言，简体中文为：zh_CN
	private String languag;
	// 用户头像
	private String headImgUrl;
	// unionid
	private String unionid;
	// 备注
	private String remark;
	// 所在分组ID
	private String groupid;

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getGroupid()
	{
		return groupid;
	}

	public void setGroupid(String groupid)
	{
		this.groupid = groupid;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public String getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLanguag() {
		return languag;
	}

	public void setLanguag(String languag) {
		this.languag = languag;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
}
