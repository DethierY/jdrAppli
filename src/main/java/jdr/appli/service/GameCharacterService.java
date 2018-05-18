package jdr.appli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdr.appli.dao.GameCharacterDao;
import jdr.appli.model.GameCharacter;

@Service
public class GameCharacterService {
	
	@Autowired
	private GameCharacterDao dao;
	
	public List<GameCharacter> getAllGameCharacters() throws Exception {
		return dao.getListGameCharacters();
	}
	
	public List<GameCharacter> getAllUserGameCharacters(Long id) throws Exception {
		return dao.getListUserGameCharacters(id);
	}
	
	public Object addGameCharacter(GameCharacter gameCharacter) throws Exception {
		String message = ": création du personnage impossible";
		if (!checkAbility(gameCharacter.getStrength()))
			return "La Force est incorrecte" + message;
		if (!checkAbility(gameCharacter.getDexterity()))
			return "La Dextérité est incorrecte" + message;
		if (!checkAbility(gameCharacter.getConstitution()))
			return "La Constitution est incorrecte" + message;
		if (!checkAbility(gameCharacter.getIntelligence()))
			return "L'Intelligence est incorrecte" + message;
		if (!checkAbility(gameCharacter.getWisdom()))
			return "La Sagesse est incorrecte" + message;
		if (!checkAbility(gameCharacter.getCharism()))
			return "Le Charisme est incorrecte" + message;
		if (!checkHeight(gameCharacter))
			return "La Taille est incorrecte" + message;
		if (!checkWeight(gameCharacter))
			return "Le Poids est incorrecte" + message;
		return dao.insertGameCharacter(gameCharacter);
	}
	
	private boolean checkHeight(GameCharacter gameCharacter) throws Exception {
		double minHeight = gameCharacter.getCharacterClass().getRace().getMinHeight();
		double maxHeight = gameCharacter.getCharacterClass().getRace().getMaxHeight();
		double sexModifier = gameCharacter.getCharacterClass().getRace().getHeightSexModifier();
		double height = gameCharacter.getHeight();
		boolean isHeightOK;
		if (gameCharacter.getSex().equals("homme")) {
			if (height >= minHeight && height <= maxHeight) {
				isHeightOK = true;
			} else {
				isHeightOK = false;
			}
		} else {
			if (height >= minHeight - sexModifier && height <= maxHeight - sexModifier) {
				isHeightOK = true;
			} else {
				isHeightOK =false;
			}
		}
		return isHeightOK;
	}
	
	private boolean checkWeight(GameCharacter gameCharacter) throws Exception {
		double minWeight = gameCharacter.getCharacterClass().getRace().getMinWeight();
		double maxWeight = gameCharacter.getCharacterClass().getRace().getMaxWeight();
		double sexModifier = gameCharacter.getCharacterClass().getRace().getWeightSexModifier();
		double weight = gameCharacter.getWeight();
		boolean isWeightOK;
		if (gameCharacter.getSex().equals("homme")) {
			if (weight >= minWeight && weight <= maxWeight) {
				isWeightOK = true;
			} else {
				isWeightOK = false;
			}
		} else {
			if (weight >= minWeight - sexModifier && weight <= maxWeight - sexModifier) {
				isWeightOK = true;
			} else {
				isWeightOK =false;
			}
		}
		return isWeightOK;
	}
	
	private boolean checkAbility(int ability) throws Exception {
		boolean isAbilityOK;
		if(ability >= 9 && ability <= 18) {
			isAbilityOK = true;
		} else {
			isAbilityOK = false;
		}
		return isAbilityOK;
	}

}
