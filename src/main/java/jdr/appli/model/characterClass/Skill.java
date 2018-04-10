package jdr.appli.model.characterClass;

import java.util.List;

public class Skill {
	
	private String name;
	private String attribute;
	private List<CharacterClass> classSkill;
	private String description;
	
	public String getName() {
		return name;
	}
	
	public String getAttribute() {
		return attribute;
	}
	
	public List<CharacterClass> getClassSkill() {
		return classSkill;
	}
	
	public String getDescription() {
		return description;
	}

}
