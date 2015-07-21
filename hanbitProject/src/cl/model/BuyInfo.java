package cl.model;

import java.util.Date;

public class BuyInfo {
	
	private int buy_num;
	private String st_id;
	private int cl_num;
	private String cl_name;
	private int cl_price;
	private Date buy_date;
	
	public int getBuy_num() {
		return buy_num;
	}
	public void setBuy_num(int buy_num) {
		this.buy_num = buy_num;
	}
	
	public String getSt_id() {
		return st_id;
	}
	public void setSt_id(String st_id) {
		this.st_id = st_id;
	}
	
	public int getCl_num() {
		return cl_num;
	}
	public void setCl_num(int cl_num) {
		this.cl_num = cl_num;
	}
	
	public String getCl_name() {
		return cl_name;
	}
	public void setCl_name(String cl_name) {
		this.cl_name = cl_name;
	}
	
	public int getCl_price() {
		return cl_price;
	}
	public void setCl_price(int cl_price) {
		this.cl_price = cl_price;
	}
	
	public Date getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(Date buy_date) {
		this.buy_date = buy_date;
	}
	
}
