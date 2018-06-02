package jdr.appli.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jdr.appli.dao.GameCharacterDAO;
import jdr.appli.dao.GetList;
import jdr.appli.model.characterClass.CharacterClass;
import jdr.appli.model.gameCharacter.GameCharacter;

@Service
public class GameCharacterService {
	
	@Autowired
	private GameCharacterDAO dao;
	
	@Autowired
	private GetList<CharacterClass> characterClassDAO;
	
	public List<GameCharacter> getAllGameCharacters() throws Exception {
		List<GameCharacter> listGameCharacters = dao.getList();
		return listGameCharacters;
	}
	
	public List<GameCharacter> getAllUserGameCharacters(Long id) throws Exception {
		List<GameCharacter> listUserGameCharacters = dao.getListForOneUser(id);
		return listUserGameCharacters;
	}
	
	public ResponseEntity<?> addGameCharacter(GameCharacter gameCharacter) throws Exception {
		String check = checkGameCharacter(gameCharacter);
		if (check != null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(check);
		} else {
			return dao.insertOne(gameCharacter);
		}
	}
	
	private String checkGameCharacter(GameCharacter gameCharacter) throws Exception {
		if (gameCharacter.getCharacterName()== null || 
			gameCharacter.getCharacterClass() == null ||
			gameCharacter.getSex() == null ||
			gameCharacter.getAlliegeance() == null) {
			return "Votre personnage est incomplet: sa sauvegarde est impossible!";
		} else {
			String message = ": création du personnage impossible!";
			if (!checkCharacterName(gameCharacter.getCharacterName()))
				return "Le nom du personnage est incorrect" + message;
			if (!checkCharacterClass(gameCharacter))
				return "La classe est incorrecte" + message;
			if (!checkSex(gameCharacter.getSex()))
				return "Le sexe est incorrect" + message;
			if (!checkHeight(gameCharacter))
				return "La Taille est incorrecte" + message;
			if (!checkWeight(gameCharacter))
				return "Le Poids est incorrect" + message;
			if (!checkStartingAge(gameCharacter))
				return "L'age est incorrect" + message;
			if (!checkEndurance(gameCharacter))
				return "L'Endurance est incorrecte" + message;
			if (!checkAlliegeance(gameCharacter.getAlliegeance()))
				return "L'Allégeance est incorrecte" + message;
			if (!checkWealth(gameCharacter))
				return "Le pécule est incorrect" + message;
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
			if (!checkAbility(gameCharacter.getCharisma()))
				return "Le Charisme est incorrect" + message;

			return null;
		}
	}
	
	private boolean checkCharacterName(String characterName) {
		boolean isCharacterNameOK;
		Pattern p = Pattern.compile("^[A-Z][a-zéèâêîôûäëïöü]*([-' ]?[A-Za-zéèâêîôûäëïöü]*){2}[a-zéèâêîôûäëïöü]$");
		Matcher m = p.matcher(characterName);
		if (m.find()) {
			isCharacterNameOK = true;
		} else {
			isCharacterNameOK = false;
		}
		return isCharacterNameOK;
	}
	
	private boolean checkCharacterClass(GameCharacter gameCharacter) throws Exception {
		boolean isCharacterClassOK;
		List<String> classNames = new ArrayList<String> ();
		List<CharacterClass> characterClasses = characterClassDAO.getList();
		for (int i= 0; i < characterClasses.size(); i++) {
			classNames.add(i, characterClasses.get(i).getClassName());
		}
		if(classNames.contains(gameCharacter.getCharacterClass().getClassName())) {
			isCharacterClassOK = true;
		} else {
			isCharacterClassOK = false;
		}
		return isCharacterClassOK;
	}
	
	private boolean checkSex(String sex) {
		boolean isSexOK;
		if (sex.equals("homme") || sex.equals("femme")) {
			isSexOK = true;
		} else {
			isSexOK = false;
		}
		return isSexOK;
	}
	
	private boolean checkHeight(GameCharacter gameCharacter) {
		double baseHeight = gameCharacter.getCharacterClass().getRace().getBaseHeight();
		double numberOfDice = gameCharacter.getCharacterClass().getRace().getHeightModifier().getNumberOfDice();
		double numberOfSides = gameCharacter.getCharacterClass().getRace().getHeightModifier().getNumberOfSides();
		double sexModifier = gameCharacter.getCharacterClass().getRace().getHeightSexModifier();
		double height = gameCharacter.getHeight();
		boolean isHeightOK;
		if (gameCharacter.getSex().equals("homme")) {
			if (height >= baseHeight + numberOfDice/100 && height <= baseHeight + numberOfDice * numberOfSides/100) {
				isHeightOK = true;
			} else {
				isHeightOK = false;
			}
		} else {
			if (height >= baseHeight - sexModifier + numberOfDice/100 && height <= baseHeight - sexModifier + numberOfDice * numberOfSides/100) {
				isHeightOK = true;
			} else {
				isHeightOK =false;
			}
		}
		return isHeightOK;
	}
	
	private boolean checkWeight(GameCharacter gameCharacter) {	
		int baseWeight = gameCharacter.getCharacterClass().getRace().getBaseWeight();
		int numberOfDice = gameCharacter.getCharacterClass().getRace().getWeightModifier().getNumberOfDice();
		int numberOfSides = gameCharacter.getCharacterClass().getRace().getWeightModifier().getNumberOfSides();
		int sexModifier = gameCharacter.getCharacterClass().getRace().getWeightSexModifier();
		int weight = gameCharacter.getWeight();
		boolean isWeightOK;
		if (gameCharacter.getSex().equals("homme")) {
			if (weight >= baseWeight + numberOfDice/2 && weight <= baseWeight + numberOfDice * numberOfSides/2) {
				isWeightOK = true;
			} else {
				isWeightOK = false;
			}
		} else {
			if (weight >= baseWeight - sexModifier + numberOfDice/2 && weight <= baseWeight - sexModifier + numberOfDice * numberOfSides/2) {
				isWeightOK = true;
			} else {
				isWeightOK =false;
			}
		}
		return isWeightOK;
	}
	
	private boolean checkStartingAge(GameCharacter gameCharacter) {
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
	
	private boolean checkEndurance(GameCharacter gameCharacter) {
		boolean isEnduranceOK;
		int dieMax = gameCharacter.getCharacterClass().getEnduranceDie().getNumberOfSides();
		int constitutionBonus = getConstitutionBonus(gameCharacter.getConstitution());
		if (gameCharacter.getEndurance() == dieMax + constitutionBonus) {
			isEnduranceOK = true;
		} else {
			isEnduranceOK = false;
		}
		return isEnduranceOK;
	}
	
	private int getConstitutionBonus (int constitution) {
		int bonus;
		if (constitution > 11) {
			bonus = (int) Math.floor(((constitution - 10) / 2));
		} else if (constitution < 10) {
			bonus = (int) Math.floor(((constitution - 11) / 2));
		} else {
			bonus = 0;
		}
		return bonus;
	}
	
	private boolean checkAlliegeance(String alliegeance) {
		boolean isAlliegeanceOK;
		if (alliegeance.equals("bien") || alliegeance.equals("neutre")) {
			isAlliegeanceOK = true;
		} else {
			isAlliegeanceOK = false;
		}
		return isAlliegeanceOK;
	}
	
	private boolean checkWealth(GameCharacter gameCharacter) {
		boolean isWealthOK;
		int characterWealth = gameCharacter.getWealth();
		int startingWealth = gameCharacter.getCharacterClass().getStartingWealth();
		if (gameCharacter.getCharacterClass().getWealthModifier() == null) {
			if (characterWealth <= startingWealth) {
				isWealthOK = true;
			} else {
				isWealthOK = false;
			}
		} else {
			int wealthModifierDice = gameCharacter.getCharacterClass().getWealthModifier().getNumberOfDice();
			int wealthModifierSides = gameCharacter.getCharacterClass().getWealthModifier().getNumberOfSides();
			if (characterWealth <= startingWealth + wealthModifierDice * wealthModifierSides) {
				isWealthOK = true;
			} else {
				isWealthOK = false;
			}
		}
		return isWealthOK;
	}

	private boolean checkAbility(int ability) {
		boolean isAbilityOK;
		if(ability >= 9 && ability <= 18) {
			isAbilityOK = true;
		} else {
			isAbilityOK = false;
		}
		return isAbilityOK;
	}
}
