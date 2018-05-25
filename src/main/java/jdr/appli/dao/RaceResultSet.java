package jdr.appli.dao;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;

import jdr.appli.model.characterClass.Race;
import jdr.appli.service.DicePoolService;

public class RaceResultSet implements ObjectResultSet {

	@Autowired
	DicePoolService dicePoolService;
	
	@Override
	public Race getObjectFromResultSet(ResultSet rs) throws Exception {
		Race race = new Race();
		race.setIdRace(rs.getLong("idRace"));
		race.setRaceName(rs.getString("raceName"));
		race.setBaseHeight(rs.getDouble("baseHeight"));
		race.setHeightSexModifier(rs.getDouble("heightSexModifier"));
		race.setHeightModifier(dicePoolService.getOneDicePool(rs.getLong("heightModifier")));
		race.setBaseWeight(rs.getInt("baseWeight"));
		race.setWeightSexModifier(rs.getInt("weightSexModifier"));
		race.setWeightModifier(dicePoolService.getOneDicePool(rs.getLong("weightModifier")));
		return race;
	}
}
