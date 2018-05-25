package jdr.appli.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import jdr.appli.model.DicePool;

public class DicePoolResultSet implements ObjectResultSet {

	@Override
	public DicePool getObjectFromResultSet(ResultSet rs) throws SQLException {
		DicePool dicePool = new DicePool();
		dicePool.setIdDicePool(rs.getLong("idDicePool"));
		dicePool.setNumberOfDice(rs.getInt("numberOfDice"));
		dicePool.setNumberOfSides(rs.getInt("numberOfSides"));
		return dicePool;
	}
}
