package jdr.appli.model;

import jdr.appli.model.characterClass.CharacterClass;

public class GameCharacter {
	
	private Long idCharacter;
	private User user;
	private Appreciation appreciation;
	private String characterName;
	private CharacterClass characterClass;
	private int level;
	
	public Long getIdCharacter() {
		return idCharacter;
	}
	
	public void setIdCharacter(Long idCharacter) {
		this.idCharacter = idCharacter;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Appreciation getAppreciation() {
		return appreciation;
	}
	
	public void setAppreciation(Appreciation appreciation) {
		this.appreciation = appreciation;
	}
	
	public String getCharacterName() {
		return characterName;
	}
	
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	
	public CharacterClass getCharacterClass() {
		return characterClass;
	}
	
	public void setCharacterClass(CharacterClass characterClass) {
		this.characterClass = characterClass;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
}
