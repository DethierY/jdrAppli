package com.character.generator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import jdr.appli.dao.GameCharacterDAO;
import jdr.appli.dao.GetList;
import jdr.appli.model.characterClass.CharacterClass;
import jdr.appli.model.characterClass.DicePool;
import jdr.appli.model.characterClass.Race;
import jdr.appli.model.gameCharacter.GameCharacter;
import jdr.appli.model.score.Appreciation;
import jdr.appli.model.score.User;
import jdr.appli.service.GameCharacterService;

@RunWith(SpringRunner.class)
public class GameCharacterServiceTest {
	
	@TestConfiguration
	static class GameCharacterServiceTestConfiguration {
		
		@Bean
		public GameCharacterService service() {
			return new GameCharacterService();
		}
	}
	
	@Autowired
	private GameCharacterService service;
		
	@MockBean
	private GameCharacterDAO dao;
		
	@MockBean
	private GetList<CharacterClass> characterClassDAO;
	
	GameCharacter characterTest;
	ResponseEntity<String> expectedResult;
	
	@Before
	public void setUp() {
		User user = new User();
		user.setIdUser(1l);
		user.setName("Drakthar");
		Appreciation appreciation = new Appreciation();
		appreciation.setIdAppreciation(5l);
		appreciation.setAverageScore(15.4);
		appreciation.setNumberOfVoters(56);
		DicePool dicePoolHeightModifier = new DicePool();
		dicePoolHeightModifier.setIdDicePool(3l);
		dicePoolHeightModifier.setNumberOfDice(5);
		dicePoolHeightModifier.setNumberOfSides(4);
		DicePool dicePoolWeightModifier = new DicePool();
		dicePoolWeightModifier.setIdDicePool(5l);
		dicePoolWeightModifier.setNumberOfDice(2);
		dicePoolWeightModifier.setNumberOfSides(6);
		Race race = new Race();
		race.setIdRace(3l);
		race.setRaceName("Dwarf");
		race.setBaseHeight(1.15);
		race.setHeightSexModifier(0.05);
		race.setHeightModifier(dicePoolHeightModifier);
		race.setBaseWeight(65);
		race.setWeightSexModifier(15);
		race.setWeightModifier(dicePoolWeightModifier);
		DicePool dicePoolAgeModifier = new DicePool();
		dicePoolAgeModifier.setIdDicePool(6l);
		dicePoolAgeModifier.setNumberOfDice(3);
		dicePoolAgeModifier.setNumberOfSides(6);
		CharacterClass characterClass = new CharacterClass();
		characterClass.setIdCharacterClass(1l);
		characterClass.setClassName("Artilleur Nain de Bor");
		characterClass.setRace(race);
		characterClass.setStartingAge(40);
		characterClass.setStartingAgeModifier(dicePoolAgeModifier);
		characterTest = new GameCharacter();
		characterTest.setUser(user);
		characterTest.setAppreciation(appreciation);
		characterTest.setCharacterName("Georges");
		characterTest.setCharacterClass(characterClass);
		
	}
	
	@Test
	public void checkCharacterNameNull() throws Exception {
		// Given
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Votre personnage est incomplet: sa sauvegarde est impossible!");
		// When
		ResponseEntity<?> testedResult = service.addGameCharacter(characterTest);
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void checkCharacterNameNotNull() throws Exception {
		// Given
		ResponseEntity<String> extpectedResult = ResponseEntity.status(HttpStatus.OK).body("Votre personnage a été créé!");
		// When
		ResponseEntity<?> testedResult = service.addGameCharacter(characterTest);
		assertEquals(expectedResult, testedResult);
	}
}
