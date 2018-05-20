package jdr.appli.dao;

import java.sql.Connection;

import jdr.appli.model.characterClass.LevelBonus;

public interface LevelBonusDao {
	
	public LevelBonus getLevelBonus(Connection con, Long id) throws Exception;

}
