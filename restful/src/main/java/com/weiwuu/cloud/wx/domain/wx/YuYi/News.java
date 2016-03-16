package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 新闻资讯服务
 * @author hui
 *
 */
public class News {
	//关键词
	private String keyword;
	//新闻类别
	private String category;
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
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
}
