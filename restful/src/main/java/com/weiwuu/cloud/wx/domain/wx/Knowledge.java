package com.weiwuu.cloud.wx.domain.wx;

/***
 * 问答知识模型
 * 
 * @author hui
 *
 */
public class Knowledge
{
	// id标识
	private int id;
	// 问题
	private String question;
	// 答案
	private String answer;
	// 对话类型 ：1.普通对话；2.笑话；3.上下文
	private int category;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

}
