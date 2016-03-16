package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 语义处理后返回的结果包装类
 * @author hui
 *
 */
public class Result {
	//文字结果
	private String text;
	//链接
	private String url;
	//图片地址
	private String imgUrl;
	//判断是否最终结果标识:true,最终结果；false，不是最终结果
	private boolean flag;
	//返回的对话
	private String dialog;
	
	public String getDialog() {
		return dialog;
	}
	public void setDialog(String dialog) {
		this.dialog = dialog;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
