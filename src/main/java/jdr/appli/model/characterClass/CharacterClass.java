package jdr.appli.model.characterClass;

public class CharacterClass {
	
	private Long idCharacterClass;
	private String className;
	private Race race;
	
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
	
}
