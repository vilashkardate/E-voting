package evoting.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import evoting.dbUtil.DBConnection;
import evoting.entity.UserDetail;

public class RegistrationDao {
	private static PreparedStatement ps,ps1;
	static{
	try {
		ps=DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=?");
		ps1=DBConnection.getConnection().prepareStatement("insert into user_details values(?,?,?,?,?,?,?,?)");
	}catch(SQLException ex)
	{
		ex.printStackTrace();
	}
	}
	
	public static boolean searchUser(String adharNo)throws SQLException
	{
		ps.setString(1, adharNo);
		return ps.executeQuery().next();
		
	}
	
	public static boolean registerUser(UserDetail user)throws SQLException
	{
		ps1.setString(1,user.getUserId());
		ps1.setString(2,user.getPassword());
		ps1.setString(3,user.getUsername());
		ps1.setString(4,user.getAddress());
		ps1.setString(5,user.getCity());
		ps1.setString(6,user.getEmail());
		ps1.setString(7,user.getMobileNo());
		ps1.setString(8,"VOTER");
		return ps1.executeUpdate()==1;
	}
	

}
