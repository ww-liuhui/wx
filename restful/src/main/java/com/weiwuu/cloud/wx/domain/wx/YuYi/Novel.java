package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 小说服务
 * @author hui
 *
 */
public class Novel {
	//小说名
	private String name;
	//小说作者
	private String author;
	//小说类型
	private String category;
	//小说章节
	private NUMBER chapter;
	//排序类型：0排序无要求（默认），1热度高优先级，2时间升序，3时间降序
	private int sort;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public NUMBER getChapter() {
		return chapter;
	}
	public void setChapter(NUMBER chapter) {
		this.chapter = chapter;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
}
