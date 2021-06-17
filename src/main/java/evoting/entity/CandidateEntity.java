package evoting.entity;

import java.io.InputStream;

public class CandidateEntity {

	private String candidateId;
	private String party;
	private String city;
	private String userid;
	private InputStream symbol;

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public InputStream getSymbol() {
		return symbol;
	}

	public void setSymbol(InputStream symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "CandidateEntity [candidateId=" + candidateId + ", party=" + party + ", city=" + city + ", userid="
				+ userid + ", symbol=" + symbol + "]";
	}

	public CandidateEntity(String candidateId, String party, String city, String userid, InputStream symbol) {
		super();
		this.candidateId = candidateId;
		this.party = party;
		this.city = city;
		this.userid = userid;
		this.symbol = symbol;
	}

	public CandidateEntity() {
		super();
	}

}
