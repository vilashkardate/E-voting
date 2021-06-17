package evoting.entity;

public class CandidateDetails {

	private String candidateId;
	private String userid;
	private String candidateName;
	private String symbol;
	private String party;
	private String city;

	public CandidateDetails() {
		super();
	}

	public CandidateDetails(String candidateId, String userid, String candidateName, String symbol, String party,
			String city) {
		super();
		this.candidateId = candidateId;
		this.userid = userid;
		this.candidateName = candidateName;
		this.symbol = symbol;
		this.party = party;
		this.city = city;
	}

	@Override
	public String toString() {
		return "CandidateDetails [candidateId=" + candidateId + ", userid=" + userid + ", candidateName="
				+ candidateName + ", symbol=" + symbol + ", party=" + party + ", city=" + city + "]";
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
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

}
