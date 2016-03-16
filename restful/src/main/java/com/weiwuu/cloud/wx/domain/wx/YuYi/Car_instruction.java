package com.weiwuu.cloud.wx.domain.wx.YuYi;

/***
 * 车载指令
 * 
 * @author hui
 *
 */
public class Car_instruction {
	// 数字，根据intent有不同的含义，例如：把温度调到20度
	private String number;
	// 窗户位置：1(司机)，2(副驾驶)，3(司机后面)，4(副驾驶后面)，5(天窗)
	private String position;
	// 操作值：OPEN (打开)，CLOSE (关闭)，MIN (最小)，MAX (最大)，UP (变大)，DOWN (变小)
	private String operator;
	//DEVICE设备
	private String DEVICE;
	// action
	private int action;
	// answer
	private String answer;
	// dialog
	private String dialog;

	public int getAction() {
		return action;
	}

	public String getDEVICE() {
		return DEVICE;
	}

	public void setDEVICE(String dEVICE) {
		DEVICE = dEVICE;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
