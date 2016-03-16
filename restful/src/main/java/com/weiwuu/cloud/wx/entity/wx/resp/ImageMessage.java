package com.weiwuu.cloud.wx.entity.wx.resp;

/***
 * 图片消息
 * 
 * @author hui
 *
 */
public class ImageMessage extends BaseMessage {
	// 图片
	private Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
