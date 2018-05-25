package jdr.appli.dao;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;

import jdr.appli.model.GameCharacter;
import jdr.appli.service.AppreciationService;
import jdr.appli.service.CharacterClassService;
import jdr.appli.service.UserService;

public class GameCharacterResultSet implements ObjectResultSet {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AppreciationService appreciationService;
	
	@Autowired
	CharacterClassService characterClassService;
	
	@Override
	public GameCharacter getObjectFromResultSet(ResultSet rs) throws Exception {
		GameCharacter character = new GameCharacter();
		character.setIdCharacter(rs.getLong("idGameCharacter"));
		character.setUser(userService.getOneUser(rs.getLong("user")));
		character.setAppreciation(appreciationService.getOneAppreciation(rs.getLong("appreciation")));
		character.setCharacterName(rs.getString("characterName"));
		character.setCharacterClass(characterClassService.getOneCharacterClass(rs.getLong("characterClass")));
		character.setAlliegeance(rs.getString("alliegeance"));
		character.setLevel(rs.getInt("level"));
		character.setAge(rs.getInt("age"));
		character.setSex(rs.getString("sex"));
		character.setHeight(rs.getDouble("height"));
		character.setWeight(rs.getInt("weight"));
		character.setStrength(rs.getInt("strength"));
		character.setDexterity(rs.getInt("dexterity"));
		character.setConstitution(rs.getInt("constitution"));
		character.setIntelligence(rs.getInt("intelligence"));
		character.setWisdom(rs.getInt("wisdom"));
		character.setCharism(rs.getInt("charism"));
		character.setEndurance(rs.getInt("endurance"));
		character.setWealth(rs.getInt("wealth"));
		return character;
	}

}
