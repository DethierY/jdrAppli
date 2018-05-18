package jdr.appli.model.characterClass;

import jdr.appli.model.DicePool;

public class Race {
	
	private Long idRace;
	private String raceName;
	private double baseHeight;
	private double minHeight;
	private double maxHeight;
	private double heightSexModifier;
	private DicePool heightModifier;
	private int baseWeight;
	private int minWeight;
	private int maxWeight;
	private int weightSexModifier;
	private DicePool weightModifier;
	
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
	
	public double getBaseHeight() {
		return baseHeight;
	}
	
	public void setBaseHeight(double baseHeight) {
		this.baseHeight = baseHeight;
	}
	
	public double getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(double minHeight) {
		this.minHeight = minHeight;
	}

	public double getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(double maxHeight) {
		this.maxHeight = maxHeight;
	}

	public double getHeightSexModifier() {
		return heightSexModifier;
	}

	public void setHeightSexModifier(double heightSexModifier) {
		this.heightSexModifier = heightSexModifier;
	}

	public DicePool getHeightModifier() {
		return heightModifier;
	}
	
	public void setHeightModifier(DicePool heightModifier) {
		this.heightModifier = heightModifier;
	}

	public int getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(int baseWeight) {
		this.baseWeight = baseWeight;
	}

	public int getMinWeight() {
		return minWeight;
	}

	public void setMinWeight(int minWeight) {
		this.minWeight = minWeight;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public int getWeightSexModifier() {
		return weightSexModifier;
	}

	public void setWeightSexModifier(int weightSexModifier) {
		this.weightSexModifier = weightSexModifier;
	}

	public DicePool getWeightModifier() {
		return weightModifier;
	}

	public void setWeightModifier(DicePool weightModifier) {
		this.weightModifier = weightModifier;
	}
	
}
