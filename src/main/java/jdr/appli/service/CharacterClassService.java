package jdr.appli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdr.appli.dao.GetList;
import jdr.appli.model.characterClass.CharacterClass;

@Service
public class CharacterClassService {
	
	@Autowired
	private GetList<CharacterClass> dao;
	
	
	public CharacterClass getOneCharacterClass(Long id) throws Exception {
		CharacterClass characterClass = dao.getOne(id);
		return characterClass;
	}
	
	public List<CharacterClass> getAllCharacterClasses() throws Exception {
		List<CharacterClass> listCharacterClasses = dao.getList();
		return listCharacterClasses;
	}
	
}
