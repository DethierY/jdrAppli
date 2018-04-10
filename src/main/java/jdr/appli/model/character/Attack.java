package jdr.appli.model.character;

import jdr.appli.model.character.goods.Weapon;

public class Attack {
	
	private int melee;
	private int range;
	private int magic;
	private int psychic;
	private int initiative;
	private int baseMagicalDefense;
	private Weapon meleeWeapon;
	private Weapon rangeWeapon;
	
	public int getMelee() {
		return melee;
	}
	
	public void setMelee(int melee) {
		this.melee = melee;
	}
	
	public int getRange() {
		return range;
	}
	
	public void setRange(int range) {
		this.range = range;
	}
	
	public int getMagic() {
		return magic;
	}
	
	public void setMagic(int magic) {
		this.magic = magic;
	}
	
	public int getPsychic() {
		return psychic;
	}
	
	public void setPsychic(int psychic) {
		this.psychic = psychic;
	}
	
	public int getInitiative() {
		return initiative;
	}
	
	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}
	
	public int getBaseMagicalDefense() {
		return baseMagicalDefense;
	}
	
	public void setBaseMagicalDefense(int baseMagicalDefense) {
		this.baseMagicalDefense = baseMagicalDefense;
	}
	
	public Weapon getMeleeWeapon() {
		return meleeWeapon;
	}
	
	public void setMeleeWeapon(Weapon meleeWeapon) {
		this.meleeWeapon = meleeWeapon;
	}
	public Weapon getRangeWeapon() {
		return rangeWeapon;
	}
	
	public void setRangeWeapon(Weapon rangeWeapon) {
		this.rangeWeapon = rangeWeapon;
	}
	
}
