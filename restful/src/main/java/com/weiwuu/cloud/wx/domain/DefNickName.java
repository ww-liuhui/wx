package com.weiwuu.cloud.wx.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DefNickName {
	private int id;
	private String name;
	private int use_num;
	
	public DefNickName() {}
	
	public DefNickName(int _id,
					   String _name,
					   int _use_num) {
		this.id = _id;
		this.name = _name;
		this.use_num = _use_num;
	}

	@JsonProperty("Id")
	public int getId() {
		return id;
	}
	@JsonProperty("Id")
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUse_num() {
		return use_num;
	}

	public void setUse_num(int use_num) {
		this.use_num = use_num;
	}
}
