package evoting.entity;

public class UserLogin {

	 private String userid;
	 private String password;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserLogin [userid=" + userid + ", password=" + password + "]";
	}
	public UserLogin(String userid, String password) {
		super();
		this.userid = userid;
		this.password = password;
	}
	public UserLogin() {
		super();
	}
	 
}
