package com.weiwuu.cloud.wx.domain.wx;

/***
 * 媒体文件信息
 * 
 * @author hui
 *
 */
public class WeixinMedia {

	// 媒体文件类型
	private String type;
	// 媒体文件标识或缩略图的文件标识
	private String mediaId;
	// 媒体文件上传时间
	private int createAt;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public int getCreateAt() {
		return createAt;
	}

	public void setCreateAt(int createAt) {
		this.createAt = createAt;
	}
}
