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
	
	public Object addGameCharacter(GameCharacter gameCharacter) throws Exception {
		if (checkHeight(gameCharacter)) {
			return dao.insertGameCharacter(gameCharacter);
		} else {
			return "La Taille est incorrecte: création du personnage impossible";
		}
			
	}
	
	private boolean checkHeight(GameCharacter gameCharacter) throws Exception {
		double minHeight = gameCharacter.getCharacterClass().getRace().getMinHeight();
		double maxHeight = gameCharacter.getCharacterClass().getRace().getMaxHeight();
		double sexModifier = gameCharacter.getCharacterClass().getRace().getHeightSexModifier();
		double height = gameCharacter.getHeight();
		boolean isHeightOK;
		if (gameCharacter.getSex().equals("homme")) {
			if (height >= minHeight && height <= maxHeight) {
				isHeightOK = true;
			} else {
				isHeightOK = false;
			}
		} else {
			if (height >= minHeight - sexModifier && height <= maxHeight - sexModifier) {
				isHeightOK = true;
			} else {
				isHeightOK =false;
			}
		}
		return isHeightOK;
	}

}
