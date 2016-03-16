package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 视频服务
 * @author hui
 *
 */
public class Video {
	//视频名
	private String name;
	//主演/嘉宾
	private String actor;
	//导演/主持人
	private String director;
	//视频类型：MOVIE（电影），TV（电视剧），COMIC（动漫），SHOW（综艺节目），OTHER（其他）
	private String category;
	//类型：动作片，剧情片，…
	private String tag;
	//地区：美国，大陆，香港，…
	private String country;
	//季，部等
	private NUMBER season;
	//集
	private NUMBER episode;
	//排序类型：0好评（默认），1热门，2经典
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
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public NUMBER getSeason() {
		return season;
	}
	public void setSeason(NUMBER season) {
		this.season = season;
	}
	public NUMBER getEpisode() {
		return episode;
	}
	public void setEpisode(NUMBER episode) {
		this.episode = episode;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
}
