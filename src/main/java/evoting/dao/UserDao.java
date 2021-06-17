package evoting.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import evoting.dbUtil.DBConnection;
import evoting.entity.UserLogin;

public class UserDao {
	private static PreparedStatement ps=null;
	static 
	{
		try
		{
			ps=DBConnection.getConnection().prepareStatement("select user_type from user_details where adhar_no=? and password=?");
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static String ValidateUser(UserLogin login)throws SQLException
	{
		String userType=null;
		ps.setString(1, login.getUserid());
		ps.setString(2, login.getPassword());
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			userType=rs.getString(1);
		}
		return userType;
	}
	

}
