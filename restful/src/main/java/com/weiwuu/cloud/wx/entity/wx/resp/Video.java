package com.weiwuu.cloud.wx.entity.wx.resp;

/***
 * 视频model
 * 
 * @author hui
 *
 */
public class Video {
	// 媒体文件ID
	private String MediaId;
	// 缩略图的媒体ID
	private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

}
