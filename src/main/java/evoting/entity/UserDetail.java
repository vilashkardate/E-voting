package evoting.entity;

public class UserDetail {
	private String username;
	private String password;
	private String userid;
	private String email;
	private String MobileNo;
	private String address;
	private String city;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserId() {
		return userid;
	}
	public void setUserId(String adharNo) {
		this.userid = adharNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return MobileNo;
	}
	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "UserDetai [username=" + username + ", password=" + password + ", adharNo=" + userid + ", email="
				+ email + ", MobileNo=" + MobileNo + ", address=" + address + ", city=" + city + "]";
	}
	public UserDetail(String username, String password, String adharNo, String email, String mobileNo, String address,
			String city) {
		super();
		this.username = username;
		this.password = password;
		this.userid = adharNo;
		this.email = email;
		MobileNo = mobileNo;
		this.address = address;
		this.city = city;
	}
	
	public UserDetail()
	{
		super();
	}
}
