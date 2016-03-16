package com.weiwuu.cloud.wx.domain.wx.YuYi;
/***
 * 查询结果
 * @author hui
 *
 */
public class StockResult {
	//股票代码
	private String cd;
	//当前价
	private String np;
	//涨幅
	private String ap;
	//涨幅比例
	private String apn;
	//最高价
	private String tp_max;
	//最低价
	private String tp_min;
	//成交量(单位：万)
	private String dn;
	//成交额(单位：亿)
	private String de;
	//市盈率
	private String pe;
	//市值(单位：亿)
	private String sz;
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getNp() {
		return np;
	}
	public void setNp(String np) {
		this.np = np;
	}
	public String getAp() {
		return ap;
	}
	public void setAp(String ap) {
		this.ap = ap;
	}
	public String getApn() {
		return apn;
	}
	public void setApn(String apn) {
		this.apn = apn;
	}
	public String getTp_max() {
		return tp_max;
	}
	public void setTp_max(String tp_max) {
		this.tp_max = tp_max;
	}
	public String getTp_min() {
		return tp_min;
	}
	public void setTp_min(String tp_min) {
		this.tp_min = tp_min;
	}
	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
		this.dn = dn;
	}
	public String getDe() {
		return de;
	}
	public void setDe(String de) {
		this.de = de;
	}
	public String getPe() {
		return pe;
	}
	public void setPe(String pe) {
		this.pe = pe;
	}
	public String getSz() {
		return sz;
	}
	public void setSz(String sz) {
		this.sz = sz;
	}
}
