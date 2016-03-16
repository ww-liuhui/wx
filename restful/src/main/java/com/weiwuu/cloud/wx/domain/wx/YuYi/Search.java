package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 网页搜索服务
 * @author hui
 *
 */
public class Search {
	//关键词
	private String keyword;
	//搜索引擎类型：google, baidu, sogou, 360, taobao,jingdong
	private String channel;
	//action
		private int action;
		//answer
		private String answer;
		//dialog
		private String dialog;
		
		public int getAction() {
			return action;
		}
		public void setAction(int action) {
			this.action = action;
		}
		public String getAnswer() {
			return answer;
		}
		public void setAnswer(String answer) {
			this.answer = answer;
		}
		public String getDialog() {
			return dialog;
		}
		public void setDialog(String dialog) {
			this.dialog = dialog;
		}
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		public String getChannel() {
			return channel;
		}
		public void setChannel(String channel) {
			this.channel = channel;
		}
}
