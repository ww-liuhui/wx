package com.weiwuu.cloud.wx.domain.wx.YuYi;

/***
 * 菜谱服务
 * 
 * @author hui
 *
 */
public class Cookbook {
	// 菜名
	private String name;
	// 菜系
	private String category;
	// 食材
	private String ingredient;
	// action
	private int action;
	// answer
	private String answer;
	// dialog
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
}
