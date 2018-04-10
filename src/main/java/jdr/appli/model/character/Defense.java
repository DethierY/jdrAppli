package jdr.appli.model.character;

import jdr.appli.model.character.goods.Armor;

public class Defense {
	
	private int ac;
	private int psychicAc;
	private String armortype;
	private int armorCheckPenalty;
	private int spellFailureChance;
	private Armor armor;
	private Armor shield;
	
	public int getAc() {
		return ac;
	}
	
	public void setAc(int ac) {
		this.ac = ac;
	}
	
	public int getPsychicAc() {
		return psychicAc;
	}
	
	public void setPsychicAc(int psychicAc) {
		this.psychicAc = psychicAc;
	}
	
	public String getArmortype() {
		return armortype;
	}
	
	public void setArmortype(String armortype) {
		this.armortype = armortype;
	}
	
	public int getArmorCheckPenalty() {
		return armorCheckPenalty;
	}
	
	public void setArmorCheckPenalty(int armorCheckPenalty) {
		this.armorCheckPenalty = armorCheckPenalty;
	}
	
	public int getSpellFailureChance() {
		return spellFailureChance;
	}
	
	public void setSpellFailureChance(int spellFailureChance) {
		this.spellFailureChance = spellFailureChance;
	}
	
	public Armor getArmor() {
		return armor;
	}
	
	public void setArmor(Armor armor) {
		this.armor = armor;
	}
	
	public Armor getShield() {
		return shield;
	}
	
	public void setShield(Armor shield) {
		this.shield = shield;
	}
	
}
