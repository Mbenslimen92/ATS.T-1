package Postuler;

public class PostulerRequest {
    private Integer candidatId;
    private Integer emploiId;
    
    
    
	public PostulerRequest() {
		super();
	}
	public PostulerRequest(Integer candidatId, Integer emploiId) {
		super();
		this.candidatId = candidatId;
		this.emploiId = emploiId;
	}
	public Integer getCandidatId() {
		return candidatId;
	}
	public void setCandidatId(Integer candidatId) {
		this.candidatId = candidatId;
	}
	public Integer getEmploiId() {
		return emploiId;
	}
	public void setEmploiId(Integer emploiId) {
		this.emploiId = emploiId;
	}

    
}
