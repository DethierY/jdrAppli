package jdr.appli.dao;

import java.sql.Connection;
import java.util.List;

import jdr.appli.model.GameCharacter;

public interface GameCharacterDao {
	
	public List<GameCharacter> getListGameCharacters(Connection con) throws Exception;
	
	public List<GameCharacter> getListUserGameCharacters(Connection con, Long id) throws Exception;
	
	public GameCharacter getGameCharacter(Connection con, Long id) throws Exception;
	
	public GameCharacter insertGameCharacter(Connection con, GameCharacter character) throws Exception;

}
