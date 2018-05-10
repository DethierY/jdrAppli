package jdr.appli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdr.appli.dao.GameCharacterDao;
import jdr.appli.model.GameCharacter;

@Service
public class GameCharacterService {
	
	@Autowired
	private GameCharacterDao dao;
	
	public List<GameCharacter> getAllGameCharacters() throws Exception {
		return dao.getListGameCharacters();
	}
	
	public List<GameCharacter> getAllUserGameCharacters(Long id) throws Exception {
		return dao.getListUserGameCharacters(id);
	}
	
	public GameCharacter addGameCharacter(GameCharacter gameCharacter) throws Exception {
			return dao.insertGameCharacter(gameCharacter);
	}

}
