package member.model;

public class UpdateMemberRequest {
	
	private String st_id;
	private String st_password;
	private String st_tel;
	private String st_address;
	private String st_email;
	
	public String getSt_id(){
		return st_id;
	}
	
	public void setSt_id(String st_id){
		this.st_id = st_id;
	}
	
	public String getSt_password(){
		return st_password;
	}
	
	public void setSt_password(String st_password){
		this.st_password = st_password;
	}
	
	public String getSt_tel(){
		return st_tel;
	}
	
	public void setSt_tel(String st_tel){
		this.st_tel = st_tel;
	}
	
	public String getSt_address(){
		return st_address;
	}
	
	public void setSt_address(String st_address){
		this.st_address = st_address;
	}
	
	public String getSt_email(){
		return st_email;
	}
	
	public void setSt_email(String st_email){
		this.st_email= st_email;
	}
	
	
	
	

}
