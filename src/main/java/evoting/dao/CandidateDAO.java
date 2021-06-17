package evoting.dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import evoting.dbUtil.DBConnection;
import evoting.entity.CandidateDetails;
import evoting.entity.CandidateEntity;
import evoting.entity.CandidateInfo;

public class CandidateDAO {
	private static PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6;
	private static Statement st;
	static {
		try {
			ps = DBConnection.getConnection().prepareStatement("select max(candidate_id) from candidate");
			ps1 = DBConnection.getConnection().prepareStatement("Select username from user_details where adhar_no=?");
			ps2 = DBConnection.getConnection().prepareStatement("Select distinct city from user_details");
			ps3 = DBConnection.getConnection().prepareStatement("insert into candidate values(?,?,?,?,?)");
			ps4 = DBConnection.getConnection().prepareStatement("select * from candidate where candidate_id =?");
			ps5 = DBConnection.getConnection().prepareStatement("delete from candidate where candidate_id=?");
			st = DBConnection.getConnection().createStatement();
			ps6 = DBConnection.getConnection().prepareStatement(
					"select candidate_id,username,party,symbol from candidate,user_details where candidate.userid=user_details.adhar_no and  candidate.city=(select city from user_details where adhar_no=?)");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static String getNewId() throws SQLException {
		ResultSet rs = ps.executeQuery();
		rs.next();
		String cid = rs.getString(1);
		if (cid == null)
			return "C101";
		else {

			int id = Integer.parseInt(cid.substring(1));
			return "C" + (id + 1);
		}

	}

	public static String getUserNameById(String uid) throws SQLException {
		ps1.setString(1, uid);
		ResultSet rs = ps1.executeQuery();
		if (rs.next()) {
			return rs.getString(1);
		} else {
			return null;
		}
	}

	public static ArrayList<String> getCity() throws SQLException {
		ArrayList<String> cityList = new ArrayList<>();
		ResultSet rs = ps2.executeQuery();
		while (rs.next()) {
			cityList.add(rs.getString(1));
		}
		return cityList;
	}

	public static boolean addCandidate(CandidateEntity cEntity) throws SQLException {
		ps3.setString(1, cEntity.getCandidateId());
		ps3.setString(2, cEntity.getParty());
		ps3.setString(3, cEntity.getUserid());
		ps3.setBinaryStream(4, cEntity.getSymbol());
		ps3.setString(5, cEntity.getCity());
		int re = ps3.executeUpdate();
		if (re > 0) {
			return true;
		}
		return false;
	}

	public static ArrayList<String> getAllCandidateId() throws SQLException {
		ArrayList<String> candidateList = new ArrayList<String>();
		ResultSet rs = st.executeQuery("select candidate_id from candidate");
		while (rs.next()) {
			candidateList.add(rs.getString(1));
		}

		return candidateList;
	}

	public static CandidateDetails getCandidateDetailByCanId(String id) throws Exception {
		CandidateDetails candidateDetails = new CandidateDetails();
		ps4.setString(1, id);
		ResultSet rs = ps4.executeQuery();
		Blob blob;
		InputStream inputStream;
		ByteArrayOutputStream byteArrayOutputStream;
		byte[] buffer;
		int bytesRead;
		byte[] imageBytes;
		String base64image;
		if (rs.next()) {
			blob = rs.getBlob(4);
			inputStream = blob.getBinaryStream();
			byteArrayOutputStream = new ByteArrayOutputStream();
			buffer = new byte[4096];
			bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				byteArrayOutputStream.write(buffer, 0, bytesRead);
			}
			imageBytes = byteArrayOutputStream.toByteArray();
			Base64.Encoder encoder = Base64.getEncoder();
			base64image = encoder.encodeToString(imageBytes);
			candidateDetails.setSymbol(base64image);
			candidateDetails.setCandidateId(id);
			candidateDetails.setCity(rs.getString(5));
			candidateDetails.setParty(rs.getString(2));
			candidateDetails.setUserid(rs.getString(3));
			candidateDetails.setCandidateName(getUserNameById(rs.getString(3)));
		}

		return candidateDetails;
	}

	public static boolean deleteCandidate(String id) throws SQLException {
		ps5.setString(1, id);
		int a = ps5.executeUpdate();
		return a > 0 ? true : false;
	}

	public static List<CandidateInfo> getCandidateInfoByCity(String userid) throws Exception {
		List<CandidateInfo> candList = new ArrayList<CandidateInfo>();
		Blob blob;
		InputStream inputStream;
		ByteArrayOutputStream byteArrayOutputStream;
		byte[] buffer;
		int bytesRead;
		byte[] imageBytes;
		String base64image;
		ps6.setString(1, userid);
		ResultSet rs = ps6.executeQuery();
		while (rs.next()) {
			CandidateInfo info = new CandidateInfo();
			blob = rs.getBlob(4);
			inputStream = blob.getBinaryStream();
			byteArrayOutputStream = new ByteArrayOutputStream();
			buffer = new byte[4096];
			bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				byteArrayOutputStream.write(buffer, 0, bytesRead);
			}
			imageBytes = byteArrayOutputStream.toByteArray();
			Base64.Encoder encoder = Base64.getEncoder();
			base64image = encoder.encodeToString(imageBytes);

			info.setCandidateId(rs.getString(1));
			info.setCandidateName(rs.getString(2));
			info.setParty(rs.getString(3));
			info.setSymbol(base64image);
			
			candList.add(info);
			
		}
		return candList;

	}

}
