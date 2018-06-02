package jdr.appli.model.score;

public class Appreciation {

	private Long idAppreciation;
	private Double averageScore;
	private int numberOfVoters;
	
	public Long getIdAppreciation() {
		return idAppreciation;
	}
	
	public void setIdAppreciation(Long idAppreciation) {
		this.idAppreciation = idAppreciation;
	}
	
	public Double getAverageScore() {
		return averageScore;
	}
	
	public void setAverageScore(Double averageScore) {
		this.averageScore = averageScore;
	}
	
	public int getNumberOfVoters() {
		return numberOfVoters;
	}
	
	public void setNumberOfVoters(int numberOfVoters) {
		this.numberOfVoters = numberOfVoters;
	}
	
}
