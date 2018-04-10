package jdr.appli.model.character;

import jdr.appli.model.User;

public class Character {
	
	private double score;
	private int numberOfVoters;
	private User owner;
	private Identity identity;
	private Abilities abilities;
	private Durability durability;
	private Saves saves;
	private Attack attack;
	private Defense defense;
	private Skills skills;
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
	
	public int getNumberOfVoters() {
		return numberOfVoters;
	}
	
	public void setNumberOfVoters(int numberOfVoters) {
		this.numberOfVoters = numberOfVoters;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public Identity getIdentity() {
		return identity;
	}
	
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}
	
	public Abilities getAbilities() {
		return abilities;
	}
	
	public void setAbilities(Abilities abilities) {
		this.abilities = abilities;
	}
	
	public Durability getDurability() {
		return durability;
	}
	
	public void setDurability(Durability durability) {
		this.durability = durability;
	}
	
	public Saves getSaves() {
		return saves;
	}
	
	public void setSaves(Saves saves) {
		this.saves = saves;
	}
	
	public Attack getAttack() {
		return attack;
	}
	
	public void setAttack(Attack attack) {
		this.attack = attack;
	}
	
	public Defense getDefense() {
		return defense;
	}
	
	public void setDefense(Defense defense) {
		this.defense = defense;
	}
	
	public Skills getSkills() {
		return skills;
	}
	
	public void setSkills(Skills skills) {
		this.skills = skills;
	}

}
