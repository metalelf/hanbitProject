package cl.model;



public class BuyRequest {
	
	private int cl_num;
	private String cl_name;
	private int cl_price;
	
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
	
	public BuyInfo toBuyInfo() {
		BuyInfo buyInfo = new BuyInfo();
		buyInfo.setCl_num(cl_num);
		buyInfo.setCl_name(cl_name);
		buyInfo.setCl_price(cl_price);
		return buyInfo;
	}
	
}
