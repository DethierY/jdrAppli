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
			return "Le Charisme est incorrect" + message;
		if (!checkHeight(gameCharacter))
			return "La Taille est incorrecte" + message;
		if (!checkWeight(gameCharacter))
			return "Le Poids est incorrect" + message;
		if (!checkAge(gameCharacter))
			return "L'age est incorrect" + message;
		return dao.insertGameCharacter(gameCharacter);
	}
	
	private boolean checkHeight(GameCharacter gameCharacter) throws Exception {
		double baseHeight = gameCharacter.getCharacterClass().getRace().getBaseHeight();
		int numberOfDice = gameCharacter.getCharacterClass().getRace().getHeightModifier().getNumberOfDice();
		int numberOfSides = gameCharacter.getCharacterClass().getRace().getHeightModifier().getNumberOfSides();
		double sexModifier = gameCharacter.getCharacterClass().getRace().getHeightSexModifier();
		double height = gameCharacter.getHeight();
		boolean isHeightOK;
		if (gameCharacter.getSex().equals("homme")) {
			if (height >= baseHeight + numberOfDice && height <= baseHeight + numberOfDice * numberOfSides) {
				isHeightOK = true;
			} else {
				isHeightOK = false;
			}
		} else {
			if (height >= baseHeight - sexModifier + numberOfDice && height <= baseHeight - sexModifier + numberOfDice * numberOfSides) {
				isHeightOK = true;
			} else {
				isHeightOK =false;
			}
		}
		return isHeightOK;
	}
	
	private boolean checkWeight(GameCharacter gameCharacter) throws Exception {	
		double baseWeight = gameCharacter.getCharacterClass().getRace().getBaseWeight();
		int numberOfDice = gameCharacter.getCharacterClass().getRace().getWeightModifier().getNumberOfDice();
		int numberOfSides = gameCharacter.getCharacterClass().getRace().getWeightModifier().getNumberOfSides();
		double sexModifier = gameCharacter.getCharacterClass().getRace().getWeightSexModifier();
		double weight = gameCharacter.getWeight();
		boolean isWeightOK;
		if (gameCharacter.getSex().equals("homme")) {
			if (weight >= baseWeight + numberOfDice && weight <= baseWeight + numberOfDice * numberOfSides) {
				isWeightOK = true;
			} else {
				isWeightOK = false;
			}
		} else {
			if (weight >= baseWeight - sexModifier + numberOfDice && weight <= baseWeight - sexModifier + numberOfDice * numberOfSides) {
				isWeightOK = true;
			} else {
				isWeightOK =false;
			}
		}
		return isWeightOK;
	}
	
	private boolean checkAge(GameCharacter gameCharacter) throws Exception {
		boolean isAgeOK;
		int numberOfDice = gameCharacter.getCharacterClass().getStartingAgeModifier().getNumberOfDice();
		int numberOfSides = gameCharacter.getCharacterClass().getStartingAgeModifier().getNumberOfSides();
		int startingAge = gameCharacter.getCharacterClass().getStartingAge();
		int age = gameCharacter.getAge();
		if (age >= startingAge + numberOfDice && age <= startingAge + numberOfDice * numberOfSides) {
			isAgeOK = true;
		} else {
			isAgeOK = false;
		}
		return isAgeOK;
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
