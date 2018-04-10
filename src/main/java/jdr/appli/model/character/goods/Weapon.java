package jdr.appli.model.character.goods;

import jdr.appli.model.characterClass.Dice;

public abstract class Weapon extends Good{

	private Dice damage;
	private String critical;
	private String type;
	private boolean unarmed;
	private int numberOfHands;
	private int rangeIncrement;
	private String race;
	
	public Dice getDamage() {
		return damage;
	}

	public String getCritical() {
		return critical;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean isUnarmed() {
		return unarmed;
	}
	
	public int getNumberOfHands() {
		return numberOfHands;
	}
	
	public String getRace() {
		return race;
	}
}