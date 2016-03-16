package com.weiwuu.cloud.wx.domain.wx;

import java.util.List;

/***
 * 关注用户列表
 * 
 * @author hui
 *
 */
public class WeixinUserList {
	// 总关注用户数
	private int total;
	// 获取的openID个数
	private int count;
	// OpenId列表
	private List<String> openIdList;
	// 拉取列表的后一个用户的openId
	private String nextOpenId;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<String> getOpenIdList() {
		return openIdList;
	}

	public void setOpenIdList(List<String> openIdList) {
		this.openIdList = openIdList;
	}

	public String getNextOpenId() {
		return nextOpenId;
	}

	public void setNextOpenId(String nextOpenId) {
		this.nextOpenId = nextOpenId;
	}
}
