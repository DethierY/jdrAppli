package jdr.appli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdr.appli.dao.CharacterClassDao;
import jdr.appli.model.characterClass.CharacterClass;

@Service
public class CharacterClassService {
	
	@Autowired
	private CharacterClassDao dao;
	
	public List<CharacterClass> getAllCharacterClasses() throws Exception {
		return dao.getListCharacterClasses();
	}
	
	public CharacterClass getOneCharacterClass(Long id) throws Exception {
		return dao.getCharacterClass(id);
	}

}
