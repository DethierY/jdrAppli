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
	
	private boolean checkHeight(GameCharacter gameCharacter) {
		boolean isHeightOK;
		double minHeight;
		double maxHeight;
		double sexModifier;
		double height = gameCharacter.getHeight();
		String race = gameCharacter.getCharacterClass().getRace().getRaceName();
		switch (race) {
			case "Humain":
				minHeight = 1.55;
				maxHeight = 2;
				sexModifier = 0.15;
				break;
			case "Dessi":
				minHeight = 1.45;
				maxHeight = 1.80;
				sexModifier = 0.05;
				break;
			case "Dwarf":
				minHeight = 1.20;
				maxHeight = 1.15;
				sexModifier = 0.05;
				break;
			default:
				minHeight = 0;
				maxHeight = 0;
				sexModifier = 0;
				break;
		}
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
