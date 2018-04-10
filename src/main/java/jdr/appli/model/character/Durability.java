package jdr.appli.model.character;

import jdr.appli.model.characterClass.Dice;

public class Durability {
	
	private int endurance;
	private Dice willpower;
	
	public int getEndurance() {
		return endurance;
	}
	
	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}
	
	public Dice getWillpower() {
		return willpower;
	}
	
	public void setWillpower(Dice willpower) {
		this.willpower = willpower;
	}

}
