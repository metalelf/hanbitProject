package cl.model;

public class ClArticle {
	
	private int cl_num;
	private String cl_name;
	private String cl_start_date;
	private String cl_end_date;
	private int cl_price;
	private String cl_time; 
	private String cl_content;
	private int cl_max;
	private int cl_current;
	
	private String te_name;
	private String te_tel;
	
	
	
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
	
	public String getCl_start_date() {
		return cl_start_date;
	}
	public void setCl_start_date(String cl_start_date) {
		this.cl_start_date = cl_start_date;
	}
	
	public String getCl_end_date() {
		return cl_end_date;
	}
	public void setCl_end_date(String cl_end_date) {
		this.cl_end_date = cl_end_date;
	}
	
	public int getCl_price() {
		return cl_price;
	}
	public void setCl_price(int cl_price) {
		this.cl_price = cl_price;
	}
	
	public String getCl_time() {
		return cl_time;
	}
	public void setCl_time(String cl_time) {
		this.cl_time = cl_time;
	}
	
	public String getCl_content() {
		return cl_content;
	}
	public void setCl_content(String cl_content) {
		this.cl_content = cl_content;
	}
	
	public int getCl_max() {
		return cl_max;
	}
	public void setCl_max(int cl_max) {
		this.cl_max = cl_max;
	}
	
	public int getCl_current() {
		return cl_current;
	}
	public void setCl_current(int cl_current) {
		this.cl_current = cl_current;
	}
	
	public String getTe_name() {
		return te_name;
	}
	public void setTe_name(String te_name) {
		this.te_name = te_name;
	}
	
	public String getTe_tel() {
		return te_tel;
	}
	public void setTe_tel(String te_tel) {
		this.te_tel = te_tel;
	}

}
