package com.weiwuu.cloud.wx.domain.wx;

/***
 * 临时二维码信息
 * 
 * @author hui
 *
 */
public class WeixinQRCode {
	// 获取的二维码ticket
	private String ticket;
	// 二维码的有效时间，最长不超过1800秒
	private int expireSeconds;
	//二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
	private String url;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(int expireSeconds) {
		this.expireSeconds = expireSeconds;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}
}
