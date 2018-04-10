package jdr.appli.model.character.goods;

public abstract class Armor extends Good {

	private int caBonus;
	private int maxDexBonus;
	private int armorCheckPenalty;
	private int spellFailureChance;
	private String type;
	
	public int getCaBonus() {
		return caBonus;
	}
	
	public int getMaxDexbonus() {
		return maxDexBonus;
	}
	
	public int getArmorCheckPenalty() {
		return armorCheckPenalty;
	}
	
	public int getSpellFailureChance() {
		return spellFailureChance;
	}
	
	public String getType() {
		return type;
	}
}
