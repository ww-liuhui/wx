package com.weiwuu.cloud.wx.domain.wx.YuYi;

/***
 * 应用服务
 * 
 * @author hui
 *
 */
public class App {
	// app名称
	private String name;
	// app类别
	private String category;
	// 排序方式：0（按质量从高到低），1（按时间从新到旧）
	private int sort;
	// 查看的类型：install（已安装），buy（已购买），update（可更新），latest（最近运行的），home（主页）
	private String type;
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

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
