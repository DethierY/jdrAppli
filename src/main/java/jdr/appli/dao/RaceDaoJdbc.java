package jdr.appli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jdr.appli.model.characterClass.Race;
import jdr.appli.service.DicePoolService;

@Repository
public class RaceDaoJdbc extends LogSQL implements RaceDao {
	
	@Autowired
	private DicePoolService dicePoolService;
	
	@Override
	public List<Race> getListRaces(Connection con) throws Exception {

		Race race;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Race> aListOfRaces = new ArrayList<Race>();
		try {
			sql = "SELECT * FROM race";
			pstmt = con.prepareStatement(sql);
			logSQL (pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				race = getRaceFromResultSet(rs);
				aListOfRaces.add(race);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL error !:" + pstmt.toString(), e);
		} finally {
			pstmt.close();
		}
		return aListOfRaces;
	}
	
	@Override
	public Race getRace(Connection con, Long id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		Race race = null;
		try {
			String sql = "SELECT * FROM race WHERE idRace = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				race = getRaceFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
		}
		return race;
	}
	
	private Race getRaceFromResultSet(ResultSet rs) throws Exception {
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
