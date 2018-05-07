package jdr.appli.dao;

import java.util.List;

import jdr.appli.model.GameCharacter;

public interface GameCharacterDao {
	
	public List<GameCharacter> getListGameCharacters() throws Exception;
	
	public GameCharacter getGameCharacter(Long id) throws Exception;

}
