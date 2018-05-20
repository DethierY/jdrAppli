package jdr.appli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdr.appli.dao.LevelBonusDao;
import jdr.appli.model.characterClass.LevelBonus;

@Service
public class LevelBonusService {

	@Autowired
	private LevelBonusDao dao;
	
	public LevelBonus getOneLevelBonus(Long id) throws Exception {
		return dao.getLevelBonus(id);
	}
}
