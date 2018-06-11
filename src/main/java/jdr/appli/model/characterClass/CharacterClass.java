package jdr.appli.model.characterClass;

public class CharacterClass {
	
	private Long idCharacterClass;
	private String className;
	private Race race;
	private int startingAge;
	private DicePool startingAgeModifier;
	private BonusProgression fortitudeSave;
	private BonusProgression reflexSave;
	private BonusProgression willSave;
	private DicePool enduranceDie;
	private int startingWealth;
	private DicePool wealthModifier;
	private Rank rank;
	
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

	public BonusProgression getFortitudeSave() {
		return fortitudeSave;
	}

	public void setFortitudeSave(BonusProgression fortitudeSave) {
		this.fortitudeSave = fortitudeSave;
	}

	public BonusProgression getReflexSave() {
		return reflexSave;
	}

	public void setReflexSave(BonusProgression reflexSave) {
		this.reflexSave = reflexSave;
	}

	public BonusProgression getWillSave() {
		return willSave;
	}

	public void setWillSave(BonusProgression willSave) {
		this.willSave = willSave;
	}

	public int getStartingWealth() {
		return startingWealth;
	}

	public void setStartingWealth(int startingWealth) {
		this.startingWealth = startingWealth;
	}
	
	public DicePool getEnduranceDie() {
		return enduranceDie;
	}

	public void setEnduranceDie(DicePool enduranceDie) {
		this.enduranceDie = enduranceDie;
	}

	public DicePool getWealthModifier() {
		return wealthModifier;
	}

	public void setWealthModifier(DicePool wealthModifier) {
		this.wealthModifier = wealthModifier;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

}
