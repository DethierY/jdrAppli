package jdr.appli.model.characterClass;

import java.util.ArrayList;
import java.util.List;

public class Dice {

	private List<Integer> dicePool;
	
	public Dice() {
		this.dicePool = new ArrayList<Integer>(2);
	}
	
	public void setNumberOfDice(int number) {
		this.dicePool.add(0, number);
	}
	
	public int getNumberofDice() {
		return dicePool.get(0);
	}
	
	public void setNumberOfSide(int number) {
		this.dicePool.add(1, number);
	}
	
	public int getNumberOfSide() {
		return dicePool.get(1);
	}
	
	public List<Integer> getDicePool() {
		return dicePool;
	}
}
