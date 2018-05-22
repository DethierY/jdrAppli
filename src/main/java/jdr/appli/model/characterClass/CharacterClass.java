package jdr.appli.model.characterClass;

import jdr.appli.model.DicePool;

public class CharacterClass {
	
	private Long idCharacterClass;
	private String className;
	private Race race;
	private int startingAge;
	private DicePool startingAgeModifier;
	private LevelBonus fortitudeSave;
	private LevelBonus reflexSave;
	private LevelBonus willSave;
	private int startingWealth;
	private DicePool wealthModifier;
	
	public Long getIdCharacterClass() {
		return idCharacterClass;
	}
	
	public void setIdCharacterClass(Long idCharacterClass) {
		this.idCharacterClass = idCharacterClass;
	}
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public int getStartingAge() {
		return startingAge;
	}

	public void setStartingAge(int startingAge) {
		this.startingAge = startingAge;
	}

	public DicePool getStartingAgeModifier() {
		return startingAgeModifier;
	}

	public void setStartingAgeModifier(DicePool startingAgeModifier) {
		this.startingAgeModifier = startingAgeModifier;
	}

	public LevelBonus getFortitudeSave() {
		return fortitudeSave;
	}

	public void setFortitudeSave(LevelBonus fortitudeSave) {
		this.fortitudeSave = fortitudeSave;
	}

	public LevelBonus getReflexSave() {
		return reflexSave;
	}

	public void setReflexSave(LevelBonus reflexSave) {
		this.reflexSave = reflexSave;
	}

	public LevelBonus getWillSave() {
		return willSave;
	}

	public void setWillSave(LevelBonus willSave) {
		this.willSave = willSave;
	}

	public int getStartingWealth() {
		return startingWealth;
	}

	public void setStartingWealth(int startingWealth) {
		this.startingWealth = startingWealth;
	}

	public DicePool getWealthModifier() {
		return wealthModifier;
	}

	public void setWealthModifier(DicePool wealthModifier) {
		this.wealthModifier = wealthModifier;
	}

}
