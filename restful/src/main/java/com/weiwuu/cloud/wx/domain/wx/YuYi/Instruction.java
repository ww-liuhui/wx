package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 通用指令
 * @author hui
 *
 */
public class Instruction {
	//数字，根据intent有不同的含义，例如：把声音调到20；
	private int number;
	//操作值：STANDARD(标准模式)，MUTE(静音模式)，VIBRA(振动模式)，INAIR(飞行模式)，RING(铃声)，WALLPAPER(壁纸)，TIME(时间)，WIFI(无线网络)，BLUETOOTH(蓝	牙)，GPS(GPS)，NET(移动网络)，SPACE(存储空间)，INPUT(输入法设置)，LANGUAGE(语言设置)，PERSONAL(个性化设置)，SCREEN(屏幕保护)，FACTORY_SETTINGS (出厂设置)，SI(系统信息)，UPDATE(系统更新)，PLAY(播放)，OPEN_MUSIC(开机音乐)，TIME_ON(定时开机)，TIME_OFF(定时关机)
	private String value;
	//操作值：OPEN (打开)，CLOSE (关闭)，MIN (最小)，MAX (最大)，UP (变大)，DOWN (变小)
	private String operator;
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
		public int getNumber() {
			return number;
		}
		public void setNumber(int number) {
			this.number = number;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getOperator() {
			return operator;
		}
		public void setOperator(String operator) {
			this.operator = operator;
		}
	
	
}
