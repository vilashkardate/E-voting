package evoting.entity;

public class Vote {
	private String candidateId;
	private String VoterId;
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getVoterId() {
		return VoterId;
	}
	public void setVoterId(String voterId) {
		VoterId = voterId;
	}
	@Override
	public String toString() {
		return "Vote [candidateId=" + candidateId + ", VoterId=" + VoterId + "]";
	}
	public Vote(String candidateId, String voterId) {
		super();
		this.candidateId = candidateId;
		VoterId = voterId;
	}
	public Vote() {
		super();
	}
	
	
}
