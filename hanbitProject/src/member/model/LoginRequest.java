package member.model;

public class LoginRequest {
	
	private String st_id;
	private String st_password;
	
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

}
