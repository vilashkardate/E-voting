package evoting.dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import evoting.dbUtil.DBConnection;
import evoting.entity.CandidateInfo;
import evoting.entity.Vote;

public class VoteDao {
	
	private static  PreparedStatement ps,ps1,ps2;
	static{
		try
		{
			ps=DBConnection.getConnection().prepareStatement("select candidate_id from voting where voter_id=?");
			ps1=DBConnection.getConnection().prepareStatement("select candidate_id,username,party,symbol from candidate, user_details where candidate.userid=user_details.adhar_no and candidate.candidate_id=?");
			ps2=DBConnection.getConnection().prepareStatement("insert into voting values(?,?)");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			}
		}
	
	public static String getCandidateId(String id)throws SQLException
	{
		ps.setString(1, id);
		ResultSet rs= ps.executeQuery();
		if(rs.next())
		{
			return rs.getString(1);
		}
		return null;
	}
	
	public static CandidateInfo getVote(String id)throws Exception
	{
		ps1.setString(1, id);
		ResultSet rs= ps1.executeQuery();
		CandidateInfo info=new  CandidateInfo();
		if(rs.next())
		{
			info.setCandidateId(rs.getString(1));
			info.setCandidateName(rs.getString(2));
			info.setParty(rs.getString(3));
			
			//code for changing image into base64
			
			Blob blob;
			InputStream inputStream;
			ByteArrayOutputStream byteArrayOutputStream;
			byte[] buffer;
			int bytesRead;
			byte[] imageBytes;
			String base64image="";
				blob=rs.getBlob(4);
				inputStream=blob.getBinaryStream();
				byteArrayOutputStream=new ByteArrayOutputStream();
				buffer=new byte[4096];
				bytesRead=-1;
				while((bytesRead=inputStream.read(buffer))!=-1)
				{
				byteArrayOutputStream.write(buffer,0,bytesRead);
				}
				imageBytes=byteArrayOutputStream.toByteArray();
				Base64.Encoder encoder=Base64.getEncoder();
				base64image=encoder.encodeToString(imageBytes);
		//debugging	
		//	System.out.print(base64image);
			info.setSymbol(base64image);			
		}
		return info;
		
	}
	
	public static boolean saveVote(Vote v) throws SQLException
	{
		ps2.setString(1, v.getCandidateId());
		ps2.setString(2,v.getVoterId());
		int ans=ps2.executeUpdate();
		if(ans>0)
			return true;
		return false;
	}

}
