package jdr.appli.dao;

import java.util.List;

import jdr.appli.model.characterClass.CharacterClass;

public interface CharacterClassDao {
	
	public List<CharacterClass> getListCharacterClasses() throws Exception;
	
	public CharacterClass getCharacterClass(Long id) throws Exception;

}
