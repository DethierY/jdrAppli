package com.character.generator;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import jdr.appli.dao.GameCharacterDAO;
import jdr.appli.dao.GetList;
import jdr.appli.model.characterClass.Bonus;
import jdr.appli.model.characterClass.BonusProgression;
import jdr.appli.model.characterClass.CharacterClass;
import jdr.appli.model.characterClass.DicePool;
import jdr.appli.model.characterClass.Race;
import jdr.appli.model.characterClass.Rank;
import jdr.appli.model.gameCharacter.GameCharacter;
import jdr.appli.model.score.Appreciation;
import jdr.appli.model.score.User;
import jdr.appli.service.GameCharacterService;

@RunWith(SpringRunner.class)
@SpringBootConfiguration
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
	
	GameCharacter gameCharacter;
	ResponseEntity<String> expectedResult;
	CharacterClass characterClass;
	List<CharacterClass> classList;
	
	@Before
	public void setUp() {
		// Mock de ka kiste de nom de classe obtenue dans checkClass()
		characterClass = new CharacterClass();
		characterClass.setClassName("Artilleur Nain de Bor");
		classList = new ArrayList<CharacterClass>() {{
			add(characterClass);
		}};
		// Instanciation d'un characterClass test
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
		DicePool enduranceDicePool = new DicePool();
		enduranceDicePool.setIdDicePool(9l);
		enduranceDicePool.setNumberOfDice(1);
		enduranceDicePool.setNumberOfSides(10);
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
		BonusProgression highBonusProgression = new BonusProgression();
		BonusProgression middleBonusProgression = new BonusProgression();
		BonusProgression lowBonusProgression = new BonusProgression();
		Bonus highBonus = new Bonus();
		Bonus middleBonus = new Bonus();
		Bonus lowBonus = new Bonus();
		ArrayList<Integer> highBonusValues = new ArrayList<Integer>() {{
			add(2); add(3); add(3); add(4); add(4); add(5); add(5); add(6); add(6); add(7); 
			add(7); add(8); add(8); add(9); add(9); add(10); add(10); add(11); add(11); add(12);
		}};
		ArrayList<Integer> middleBonusValues = new ArrayList<Integer>() {{
			add(1); add(1); add(2); add(2); add(3); add(3); add(3); add(4); add(4); add(5); 
			add(5); add(6); add(6); add(6); add(7); add(7); add(8); add(8); add(9); add(9);
		}};
		ArrayList<Integer> lowBonusValues = new ArrayList<Integer>() {{
			add(0); add(0); add(1); add(1); add(1); add(2); add(2); add(2); add(3); add(3); 
			add(3); add(4); add(4); add(4); add(5); add(5); add(5); add(6); add(6); add(6);
		}};
		ArrayList<Bonus> highProgressionValue = new ArrayList<Bonus>();
		ArrayList<Bonus> middleProgressionValue = new ArrayList<Bonus>();
		ArrayList<Bonus> lowProgressionValue = new ArrayList<Bonus>();
		for(int i = 0; i <20; i++ ) {
			int level = 1+i;
			highBonus.setIdBonus(40l+i);
			highBonus.setLevelValue(level);
			highBonus.setBonusValue(highBonusValues.get(i));
			highProgressionValue.add(highBonus);
			middleBonus.setIdBonus(21l+i);
			middleBonus.setLevelValue(level);
			middleBonus.setBonusValue(middleBonusValues.get(i));
			lowBonus.setIdBonus(1l+i);
			lowBonus.setLevelValue(level);
			lowBonus.setBonusValue(lowBonusValues.get(i));
			lowProgressionValue.add(lowBonus);
		}
		highBonusProgression.setIdBonusProgression(3l);
		highBonusProgression.setProgressionValue(highProgressionValue);
		middleBonusProgression.setIdBonusProgression(2l);
		middleBonusProgression.setProgressionValue(middleProgressionValue);
		lowBonusProgression.setIdBonusProgression(1l); 
		lowBonusProgression.setProgressionValue(lowProgressionValue);
		ArrayList<String> rankValues = new ArrayList<String>() {{
			add("Recrue"); add("Apprenti-Artilleur"); add("Stagiaire"); add("Soldat"); add("Mercenaire"); add("Sergent-Mercenaire"); add("Pointeur"); add("Chargeur"); add("Fusilier"); add("Sergent-Fusilier"); 
			add("Officier-Fusilier"); add("Artilleur"); add("Chef de Pièce"); add("Chef de batterie"); add("Cannonier"); add("Officier"); add("Lieutenant-Connonier"); add("Capitaine de Batterie"); add("Général"); add("Grand Général");
		}};
		ArrayList<Rank> ranksList = new ArrayList<Rank>();
		for (int i = 0; i<20; i++) {
			Rank rank = new Rank();
			rank.setIdRank(1l+i);
			rank.setLevelValue(1+i);
			rank.setRankValue(rankValues.get(i));
			ranksList.add(rank);
		}
		CharacterClass characterClass = new CharacterClass();
		characterClass.setIdCharacterClass(1l);
		characterClass.setClassName("Artilleur Nain de Bor");
		characterClass.setRace(race);
		characterClass.setStartingAge(40);
		characterClass.setStartingAgeModifier(dicePoolAgeModifier);
		characterClass.setFortitudeSave(highBonusProgression);
		characterClass.setReflexSave(lowBonusProgression);
		characterClass.setWillSave(middleBonusProgression);
		characterClass.setEnduranceDie(enduranceDicePool);
		characterClass.setStartingWealth(160);
		characterClass.setWealthModifier(null);
		characterClass.setRanks(ranksList);
		gameCharacter = new GameCharacter();
		gameCharacter.setIdCharacter(1l);
		gameCharacter.setUser(user);
		gameCharacter.setAppreciation(appreciation);
		gameCharacter.setCharacterName("Georges");
		gameCharacter.setCharacterClass(characterClass);
		gameCharacter.setAlliegeance("neutre");
		gameCharacter.setLevel(1);
		gameCharacter.setAge(51);
		gameCharacter.setSex("homme");
		gameCharacter.setHeight(1.27);
		gameCharacter.setWeight(70);
		gameCharacter.setStrength(10);
		gameCharacter.setDexterity(16);
		gameCharacter.setConstitution(10);
		gameCharacter.setIntelligence(13);
		gameCharacter.setWisdom(17);
		gameCharacter.setCharisma(15);
		gameCharacter.setEndurance(10);
		gameCharacter.setWealth(160);
	}
	
	@Test
	public void CheckConformMaleCharacterWithGoodAlliegeance() throws Exception {
		// Given
		gameCharacter.setAlliegeance("bien");
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.OK).body("Votre personnage a été créé!");
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		Mockito.when(dao.insertOne(gameCharacter)).thenReturn(ResponseEntity.status(HttpStatus.OK).body("Votre personnage a été créé!"));
		// When
		ResponseEntity<String> testedResult = service.addGameCharacter(gameCharacter);
		//then
		assertEquals(expectedResult, testedResult);
		Mockito.verify(dao, Mockito.times(1)).insertOne(gameCharacter);
	}
	
	@Test
	public void CheckConformMaleCharacterWithHighConstitution() throws Exception {
		// Given
		gameCharacter.setConstitution(18);
		gameCharacter.setEndurance(14);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.OK).body("Votre personnage a été créé!");
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		Mockito.when(dao.insertOne(gameCharacter)).thenReturn(ResponseEntity.status(HttpStatus.OK).body("Votre personnage a été créé!"));
		// When
		ResponseEntity<String> testedResult = service.addGameCharacter(gameCharacter);
		//then
		assertEquals(expectedResult, testedResult);
		Mockito.verify(dao, Mockito.times(1)).insertOne(gameCharacter);
	}
	
	@Test
	public void CheckConformMaleCharacterWithLowConstitution() throws Exception {
		// Given
		gameCharacter.setConstitution(9);
		gameCharacter.setEndurance(9);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.OK).body("Votre personnage a été créé!");
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		Mockito.when(dao.insertOne(gameCharacter)).thenReturn(ResponseEntity.status(HttpStatus.OK).body("Votre personnage a été créé!"));
		// When
		ResponseEntity<String> testedResult = service.addGameCharacter(gameCharacter);
		//then
		assertEquals(expectedResult, testedResult);
		Mockito.verify(dao, Mockito.times(1)).insertOne(gameCharacter);
	}
	
	@Test
	public void checkConformFemaleCharacter() throws Exception {
		//given
		gameCharacter.setSex("femme");
		gameCharacter.setWeight(53);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.OK).body("Votre personnage a été créé!");
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		Mockito.when(dao.insertOne(gameCharacter)).thenReturn(ResponseEntity.status(HttpStatus.OK).body("Votre personnage a été créé!"));
		// When
		ResponseEntity<String> testedResult = service.addGameCharacter(gameCharacter);
		// Then
		assertEquals(expectedResult, testedResult);
		Mockito.verify(dao, Mockito.times(1)).insertOne(gameCharacter);
	}
		
	
	@Test
	public void checkCharacterNameNull() throws Exception {
		// Given
		gameCharacter.setCharacterName(null);
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Votre personnage est incomplet: sa sauvegarde est impossible!");
		// When
		ResponseEntity<String> testedResult = service.addGameCharacter(gameCharacter);
		// Then
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void checkCharacterClassNull() throws Exception {
		//Given
		gameCharacter.setCharacterClass(null);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Votre personnage est incomplet: sa sauvegarde est impossible!");
		// When
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<String> testedResult = service.addGameCharacter(gameCharacter);
		// Then
		assertEquals(expectedResult, testedResult);
	}

	@Test
	public void checkCharacterSexNull() throws Exception {
		//Given
		gameCharacter.setSex(null);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Votre personnage est incomplet: sa sauvegarde est impossible!");
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		// When
		ResponseEntity<String> testedResult = service.addGameCharacter(gameCharacter);
		// Then
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void checkCharacterAlliegeanceNull() throws Exception {
		//Given
		gameCharacter.setAlliegeance(null);
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Votre personnage est incomplet: sa sauvegarde est impossible!");
		// When
		ResponseEntity<String> testedResult = service.addGameCharacter(gameCharacter);
		// Then
		assertEquals(expectedResult, testedResult);
	}

	@Test
	public void checkNotConformCharacterName() throws Exception {
		//given
		gameCharacter.setCharacterName("julius4");
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Le nom est incorrect: création du personnage impossible!");
		// When
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		// Then
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void checkNotConformClass() throws Exception {
		// Given
		CharacterClass characterClass = gameCharacter.getCharacterClass();
		characterClass.setClassName("Guerrier");
		gameCharacter.setCharacterClass(characterClass);
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La classe est incorrecte: création du personnage impossible!");
		// When
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		// Then
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void checkNotConformSex() throws Exception {
		//given
		gameCharacter.setSex("neutre");
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Le sexe est incorrect: création du personnage impossible!");
		// When
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		// Then
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void checkTooMuchFemaleHightHeight() throws Exception {
		//given
		gameCharacter.setSex("femme");
		gameCharacter.setWeight(53);
		gameCharacter.setHeight(2.6);
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La taille est incorrecte: création du personnage impossible!");
		// When
		ResponseEntity<String> testedResult = service.addGameCharacter(gameCharacter);
		// Then
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void checkTooMuchFemaleLowtHeight() throws Exception {
		//given
		gameCharacter.setSex("femme");
		gameCharacter.setWeight(53);
		gameCharacter.setHeight(0.5);
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La taille est incorrecte: création du personnage impossible!");
		// When
		ResponseEntity<String> testedResult = service.addGameCharacter(gameCharacter);
		// Then
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void checkTooMuchMaleHightHeight() throws Exception {
		//given
		gameCharacter.setHeight(2.6);
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La taille est incorrecte: création du personnage impossible!");
		// When
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		// Then
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void checkTooMuchMaleLowtHeight() throws Exception {
		//given
		gameCharacter.setHeight(0.5);
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La taille est incorrecte: création du personnage impossible!");
		// When
		ResponseEntity<String> testedResult = service.addGameCharacter(gameCharacter);
		// Then
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void checkTooMuchFemaleHighWeight() throws Exception {
		//given
		gameCharacter.setSex("femme");
		gameCharacter.setWeight(150);
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Le poids est incorrect: création du personnage impossible!");
		// When
		ResponseEntity<String> testedResult = service.addGameCharacter(gameCharacter);
		// Then
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void checkTooMuchFemaleLowtweight() throws Exception {
		//Given
		gameCharacter.setSex("femme");
		gameCharacter.setWeight(15);
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Le poids est incorrect: création du personnage impossible!");
		// When
		ResponseEntity<String> testedResult = service.addGameCharacter(gameCharacter);
		// Then
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void checkTooMuchMaleHighWeight() throws Exception {
		//given
		gameCharacter.setWeight(150);
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Le poids est incorrect: création du personnage impossible!");
		// When
		ResponseEntity<String> testedResult = service.addGameCharacter(gameCharacter);
		// Then
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void checkTooMuchMaleLowhWeight() throws Exception {
		//given
		gameCharacter.setWeight(10);
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Le poids est incorrect: création du personnage impossible!");
		// When
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		// Then
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void CheckTooMuchHighStrength() throws Exception {
		//given
		gameCharacter.setStrength(30);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La Force est incorrecte: création du personnage impossible!");
		// When
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<String> testedResult = service.addGameCharacter(gameCharacter);
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void CheckTooMuchLowStrength() throws Exception {
		//given
		gameCharacter.setStrength(7);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La Force est incorrecte: création du personnage impossible!");
		// When
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void CheckTooMuchHighDexterity() throws Exception {
		//given
		gameCharacter.setDexterity(30);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La Dextérité est incorrecte: création du personnage impossible!");
		// When
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void CheckTooMuchLowDexterity() throws Exception {
		//given
		gameCharacter.setDexterity(7);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La Dextérité est incorrecte: création du personnage impossible!");
		// When
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void CheckTooMuchHighConstitution() throws Exception {
		//given
		gameCharacter.setConstitution(30);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La Constitution est incorrecte: création du personnage impossible!");
		// When
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void CheckTooMuchLowConstitution() throws Exception {
		//given
		gameCharacter.setConstitution(7);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La Constitution est incorrecte: création du personnage impossible!");
		// When
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void CheckTooMuchHighIntelligence() throws Exception {
		//given
		gameCharacter.setIntelligence(30);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("L'Intelligence est incorrecte: création du personnage impossible!");
		// When
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void CheckTooMuchLowIntelligence() throws Exception {
		//given
		gameCharacter.setIntelligence(7);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("L'Intelligence est incorrecte: création du personnage impossible!");
		// When
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void CheckTooMuchHighWisdom() throws Exception {
		//given
		gameCharacter.setWisdom(30);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La Sagesse est incorrecte: création du personnage impossible!");
		// When
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void CheckTooMuchLowWisdom() throws Exception {
		//given
		gameCharacter.setWisdom(7);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La Sagesse est incorrecte: création du personnage impossible!");
		// When
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void CheckTooMuchHighCharisma() throws Exception {
		//given
		gameCharacter.setCharisma(30);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Le Charisme est incorrect: création du personnage impossible!");
		// When
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void CheckTooMuchLowCharisma() throws Exception {
		//given
		gameCharacter.setCharisma(7);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Le Charisme est incorrect: création du personnage impossible!");
		// When
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void checkTooMuchHighStartingAge() throws Exception {
		//given
		gameCharacter.setAge(150);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("L'âge est incorrect: création du personnage impossible!");
		// When
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void checkTooMuchLowStartingAge() throws Exception {
		//given
		gameCharacter.setAge(10);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("L'âge est incorrect: création du personnage impossible!");
		// When
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		assertEquals(expectedResult, testedResult);
	}

	@Test
	public void checkNotConformEndurance() throws Exception {
		//given
		gameCharacter.setEndurance(150);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("L'Endurance est incorrecte: création du personnage impossible!");
		// When
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void checkNotConformAlliegeance() throws Exception {
		//given
		gameCharacter.setAlliegeance("mal");
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("L'allégeance est incorrecte: création du personnage impossible!");
		// When
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void checkNotConformStartingWealth() throws Exception {
		//given
		gameCharacter.setWealth(200);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Le pécule est incorrect: création du personnage impossible!");
		// When
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		assertEquals(expectedResult, testedResult);
	}
	
	@Test
	public void checkConformStartingWealthWithModifier() throws Exception {
		//given
		DicePool dicePool = new DicePool();
		dicePool.setIdDicePool(1l);
		dicePool.setNumberOfDice(5);
		dicePool.setNumberOfSides(10);
		CharacterClass characterClass = gameCharacter.getCharacterClass();
		characterClass.setWealthModifier(dicePool);
		gameCharacter.setCharacterClass(characterClass);
		gameCharacter.setWealth(190);
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		Mockito.when(dao.insertOne(gameCharacter)).thenReturn(ResponseEntity.status(HttpStatus.OK).body("Votre personnage a été créé!"));
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.OK).body("Votre personnage a été créé!");
		// When
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		// Then
		assertEquals(expectedResult, testedResult);
		Mockito.verify(dao, Mockito.times(1)).insertOne(gameCharacter);
	}
	
	@Test
	public void checkNotConformStartingWealthWithModifier() throws Exception {
		//given
		DicePool dicePool = new DicePool();
		dicePool.setIdDicePool(1l);
		dicePool.setNumberOfDice(5);
		dicePool.setNumberOfSides(10);
		CharacterClass characterClass = gameCharacter.getCharacterClass();
		characterClass.setWealthModifier(dicePool);
		gameCharacter.setCharacterClass(characterClass);
		gameCharacter.setWealth(230);
		Mockito.when(characterClassDAO.getList()).thenReturn(classList);
		ResponseEntity<String> expectedResult = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Le pécule est incorrect: création du personnage impossible!");
		// When
		ResponseEntity<?> testedResult = service.addGameCharacter(gameCharacter);
		// Then
		assertEquals(expectedResult, testedResult);
	}
}

	
