package jdr.appli.dao;

import java.sql.Connection;
import java.util.List;

import jdr.appli.model.CreationResponse;
import jdr.appli.model.GameCharacter;

public interface GameCharacterDao {
	
	public List<GameCharacter> getListGameCharacters(Connection con) throws Exception;
	
	public List<GameCharacter> getListUserGameCharacters(Connection con, Long id) throws Exception;
	
	public GameCharacter getGameCharacter(Connection con, Long id) throws Exception;
	
	public CreationResponse insertGameCharacter(Connection con, GameCharacter character) throws Exception;

}
