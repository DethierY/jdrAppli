package jdr.appli.model.characterClass;

public class DicePool {
	
	private Long idDicePool;
	private int numberOfDice;
	private int numberOfSides;
	
	public Long getIdDicePool() {
		return idDicePool;
	}

	public void setIdDicePool(Long idDicePool) {
		this.idDicePool = idDicePool;
	}

	public int getNumberOfDice() {
		return numberOfDice;
	}
	
	public void setNumberOfDice(int numberOfDice) {
		this.numberOfDice = numberOfDice;
	}
	
	public int getNumberOfSides() {
		return numberOfSides;
	}
	
	public void setNumberOfSides(int numberOfSides) {
		this.numberOfSides = numberOfSides;
	}

}
