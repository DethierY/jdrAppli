package jdr.appli.model;

import jdr.appli.model.characterClass.CharacterClass;
import jdr.appli.model.characterClass.User;

public class GameCharacter {
	
	private Long idCharacter;
	private User user;
	private Appreciation appreciation;
	private String characterName;
	private CharacterClass characterClass;
	private String alliegeance;
	private int level;
	private String sex;
	private double height;
	private int strength;
	private int dexterity;
	private int constitution;
	private int intelligence;
	private int wisdom;
	private int charism;
	
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
	
	public String getAlliegeance() {
		return alliegeance;
	}

	public void setAlliegeance(String alliegeance) {
		this.alliegeance = alliegeance;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getWisdom() {
		return wisdom;
	}

	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

	public int getCharism() {
		return charism;
	}

	public void setCharism(int charism) {
		this.charism = charism;
	}
	
}
