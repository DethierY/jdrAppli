package jdr.appli.model.characterClass;

import java.util.List;

import jdr.appli.model.character.goods.Armor;
import jdr.appli.model.character.goods.Weapon;

public abstract class CharacterClass {
	
	private String name;
	private LevelBonus baseCombatSkill;
	private LevelBonus baseMagicalCombatSkill;
	private Dice baseMagicalDamage;
	private LevelBonus fortSave;
	private LevelBonus refSave;
	private LevelBonus willSave;
	private Dice enduranceDie;
	private List<Dice> willpower;
	private int baseSpeed;
	private List<Integer> skillPoints;
	private List<Skill> classSkills;
	private List<Armor> armorProficiency;
	private List<Armor> shieldProficiency;
	private List<Weapon> weaponProficiency;
	private String rank;
	
	public String getName() {
		return name;
	}
	
	public LevelBonus getBaseCombatSkill() {
		return baseCombatSkill;
	}
	
	public LevelBonus getBaseMagicalCombatSkill() {
		return baseMagicalCombatSkill;
	}
	
	public Dice getBaseMagicalDamage() {
		return baseMagicalDamage;
	}
	
	public LevelBonus getFortSave() {
		return fortSave;
	}
	
	public LevelBonus getRefSave() {
		return refSave;
	}
	
	public LevelBonus getWillSave() {
		return willSave;
	}
	
	public Dice getEnduranceDie() {
		return enduranceDie;
	}
	
	public List<Dice> getWillPower() {
		return willpower;
	}
	
	public int getBaseSpeed() {
		return baseSpeed;
	}
	
	public List<Integer> getSkillPoints() {
		return skillPoints;
	}
	
	public List<Skill> getClassSkills() {
		return classSkills;
	}
	
	public List<Armor> getArmorProficiency() {
		return armorProficiency;
	}
	
	public List<Armor> getShieldProficiency() {
		return shieldProficiency;
	}
	
	public List<Weapon> getWeaponProficiency() {
		return weaponProficiency;
	}
	
	public String getRank() {
		return rank;
	}
}
