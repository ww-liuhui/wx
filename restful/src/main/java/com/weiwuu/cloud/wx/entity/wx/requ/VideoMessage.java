package com.weiwuu.cloud.wx.entity.wx.requ;

/**
 * 视频消息
 * 
 * @author liufeng
 * @date 2013-05-19
 */
public class VideoMessage {
	// 视频消息媒体ID
	private String MediaId;
	// 视频消息缩略图的媒体ID
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
