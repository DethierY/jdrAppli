package jdr.appli.model.characterClass;

import jdr.appli.model.DicePool;

public class Race {
	
	private Long idRace;
	private String raceName;
	private double maleBaseHeight;
	private double femaleBaseHeight;
	private DicePool heightModifier;
	
	public Long getIdRace() {
		return idRace;
	}
	
	public void setIdRace(Long idRace) {
		this.idRace = idRace;
	}
	
	public String getRaceName() {
		return raceName;
	}
	
	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}
	
	public double getMaleBaseHeight() {
		return maleBaseHeight;
	}
	
	public void setMaleBaseHeight(double maleBaseHeight) {
		this.maleBaseHeight = maleBaseHeight;
	}
	
	public double getFemaleBaseHeight() {
		return femaleBaseHeight;
	}
	
	public void setFemaleBaseHeight(double femaleBaseHeight) {
		this.femaleBaseHeight = femaleBaseHeight;
	}
	
	public DicePool getHeightModifier() {
		return heightModifier;
	}
	
	public void setHeightModifier(DicePool heightModifier) {
		this.heightModifier = heightModifier;
	}
	
}
