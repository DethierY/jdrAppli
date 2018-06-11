package jdr.appli.model.characterClass;

import java.util.List;

public class BonusProgression {
	
	private Long idBonusProgression;
	private List<Bonus> progressionValue;
	
	public Long getIdBonusProgression() {
		return idBonusProgression;
	}

	public void setIdBonusProgression(Long idBonusProgression) {
		this.idBonusProgression = idBonusProgression;
	}

	public List<Bonus> getProgressionValue() {
		return progressionValue;
	}

	public void setProgressionValue(List<Bonus> bonusValues) {
		this.progressionValue = bonusValues;
	}

}
