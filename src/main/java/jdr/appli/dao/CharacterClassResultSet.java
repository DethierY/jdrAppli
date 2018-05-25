package jdr.appli.dao;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;

import jdr.appli.model.characterClass.CharacterClass;
import jdr.appli.service.DicePoolService;
import jdr.appli.service.LevelBonusService;
import jdr.appli.service.RaceService;
import jdr.appli.service.RankService;

public class CharacterClassResultSet implements ObjectResultSet {
	
	@Autowired
	RaceService raceService;
	
	@Autowired
	DicePoolService dicePoolService;
	
	@Autowired
	LevelBonusService levelBonusService;
	
	@Autowired
	RankService rankService;
	
	@Override
	public CharacterClass getObjectFromResultSet(ResultSet rs) throws Exception {
		CharacterClass characterClass = new CharacterClass();
		characterClass.setIdCharacterClass(rs.getLong("idCharacterClass"));
		characterClass.setClassName(rs.getString("className"));
		if (raceService == null) {
			System.out.println("raceService = null");
		} else {
			System.out.println("raceService = pas null");
		}
		characterClass.setRace(raceService.getOneRace(rs.getLong("race")));
		characterClass.setStartingAge(rs.getInt("startingAge"));
		characterClass.setStartingAgeModifier(dicePoolService.getOneDicePool(rs.getLong("startingAgeModifier")));
		characterClass.setFortitudeSave(levelBonusService.getOneLevelBonus(rs.getLong("fortitudeSave")));
		characterClass.setReflexSave(levelBonusService.getOneLevelBonus(rs.getLong("reflexSave")));
		characterClass.setWillSave(levelBonusService.getOneLevelBonus(rs.getLong("willSave")));
		characterClass.setEnduranceDie(dicePoolService.getOneDicePool(rs.getLong("enduranceDie")));
		characterClass.setStartingWealth(rs.getInt("startingWealth"));
		characterClass.setWealthModifier(dicePoolService.getOneDicePool(rs.getLong("wealthModifier")));
		characterClass.setRank(rankService.getOneRank(rs.getLong("rank")));
		return characterClass;
	}

}
