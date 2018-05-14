package jdr.appli.model;

public class DicePool {
	
	private Long idDicePool;
	private int numberOfDices;
	private int numberOfSides;
	
	public Long getIdDicePool() {
		return idDicePool;
	}

	public void setIdDicePool(Long idDicePool) {
		this.idDicePool = idDicePool;
	}

	public int getNumberOfDices() {
		return numberOfDices;
	}
	
	public void setNumberOfDices(int numberOfDices) {
		this.numberOfDices = numberOfDices;
	}
	
	public int getNumberOfSides() {
		return numberOfSides;
	}
	
	public void setNumberOfSides(int numberOfSides) {
		this.numberOfSides = numberOfSides;
	}

}
