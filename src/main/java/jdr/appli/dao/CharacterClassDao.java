package jdr.appli.dao;

import java.sql.Connection;
import java.util.List;

import jdr.appli.model.characterClass.CharacterClass;

public interface CharacterClassDao {
	
	public List<CharacterClass> getListCharacterClasses(Connection con) throws Exception;
	
	public CharacterClass getCharacterClass(Connection con, Long id) throws Exception;

}
