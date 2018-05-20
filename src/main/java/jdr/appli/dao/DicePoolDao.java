package jdr.appli.dao;

import java.sql.Connection;

import jdr.appli.model.DicePool;

public interface DicePoolDao {
	
	public DicePool getDicePool(Connection con, Long id) throws Exception;

}
