package evoting.dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection conn=null;
	
	static {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");  
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/evoting","root","Vilash@123");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public static Connection getConnection()
	{
		return conn;
	}
	public static void closeConnection()
	{
		if(conn!=null)
		{
			try {
				conn.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
