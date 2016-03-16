package com.weiwuu.cloud.wx.domain.wx.YuYi;

import java.util.ArrayList;

/***
 * 语义输出协议
 * @author hui
 *
 */
public class YYout {
	
	//用于标识用户请求后的状态
	private int errcode;
	//用户的输入字符串
	private String query;
	//服务的全局类别id
	private String type;
	//语义理解后的结构化标识，各个服务不同
	private Semantic semantic;
	//部分类别的结果
	private ArrayList<Object> result;
	//部分类别的结果html5展示，目前不支持
	private String answer;
	//特殊回复说明
	private String text;
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Semantic getSemantic() {
		return semantic;
	}
	public void setSemantic(Semantic semantic) {
		this.semantic = semantic;
	}
	public ArrayList<Object> getResult() {
		return result;
	}
	public void setResult(ArrayList<Object> result) {
		this.result = result;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
