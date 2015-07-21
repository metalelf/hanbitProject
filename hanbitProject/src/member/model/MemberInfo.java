package member.model;

import java.util.Date;

public class MemberInfo {
	
	private int st_num; //수강생 번호
	private String st_name; //이름
	private String st_id; //아이디
	private String st_password; //비밀번호
	private String st_tel; //연락처
	private String st_address; //주소
	private String st_email;
	private Date join_date;
	//private int cl_num; //수업 번호(FK)
	
	public int getSt_num() {
		return st_num;
	}
	public void setSt_num(int st_num) {
		this.st_num = st_num;
	}
	
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
	
	public String getSt_name(){
		return st_name;
	}
	
	public void setSt_name(String st_name){
		this.st_name = st_name;
	}
	
	public String getSt_email(){
		return st_email;
	}
	
	public void setSt_email(String st_email){
		this.st_email = st_email;
	}
	
	public Date getJoin_date() {
		return join_date;
	}

	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}
	
	public MemberInfo toMemberInfo() {
		MemberInfo memberInfo = new MemberInfo();
		memberInfo.setSt_id(st_id);
		memberInfo.setSt_password(st_password);
		memberInfo.setSt_name(st_name);
		memberInfo.setSt_tel(st_tel);
		memberInfo.setSt_address(st_address);
		memberInfo.setSt_email(st_email);
		return memberInfo;
	}
	
	/*public int getClNum(){
		return cl_num;
	}
	
	public void setClNum(int cl_num){
		this.cl_num = cl_num;
	}
*/
}
