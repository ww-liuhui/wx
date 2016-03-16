package com.weiwuu.cloud.wx.entity.wx.resp;

/**
 * 语音消息
 * 
 * @author liufeng
 * @date 2013-05-19
 */
public class VoiceMessage extends BaseMessage {
	// 语音
	private Voice voice;

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

}