package jdr.appli.model.characterClass;

import jdr.appli.model.DicePool;

public class CharacterClass {
	
	private Long idCharacterClass;
	private String className;
	private Race race;
	private int startingAge;
	private DicePool startingAgeModifier;
	private int fortitudeSave;
	private int reflexSave;
	private int willSave;
	
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

	public int getFortitudeSave() {
		return fortitudeSave;
	}

	public void setFortitudeSave(int fortitudeSave) {
		this.fortitudeSave = fortitudeSave;
	}

	public int getReflexSave() {
		return reflexSave;
	}

	public void setReflexSave(int reflexSave) {
		this.reflexSave = reflexSave;
	}

	public int getWillSave() {
		return willSave;
	}

	public void setWillSave(int willSave) {
		this.willSave = willSave;
	}
	
}
