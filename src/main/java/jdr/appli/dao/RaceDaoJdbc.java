package jdr.appli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jdr.appli.model.DicePool;
import jdr.appli.model.characterClass.Race;

@Repository
public class RaceDaoJdbc implements RaceDao {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private DataSource datasource;

	@Autowired
	public RaceDaoJdbc(JdbcTemplate jdbcTemplate) {
		this.datasource = jdbcTemplate.getDataSource();
	}
	
	@Override
	public List<Race> getListRaces() throws Exception {
		Connection con = datasource.getConnection();
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
			con.close();
		}
		return aListOfRaces;
	}
	
	@Override
	public Race getRace(Long id) throws Exception {
		Connection con = datasource.getConnection();
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
			con.close();
		}
		return race;
	}
	
	private DicePool getDicePool(Long id) throws Exception {
		Connection con = datasource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		DicePool dicePool = null;
		try {
			String sql = "SELECT * FROM dicePool WHERE idDicePool = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				dicePool = getDicePoolFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return dicePool;
	}
	
	private Race getRaceFromResultSet(ResultSet rs) throws Exception {
		Race race = new Race();
		race.setIdRace(rs.getLong("idRace"));
		race.setRaceName(rs.getString("raceName"));
		race.setMaleBaseHeight(rs.getDouble("maleBaseHeight"));
		race.setFemaleBaseHeight(rs.getDouble("femaleBaseHeight"));
		race.setHeightModifier(getDicePool(rs.getLong("heightModifier")));
		return race;
	}
	
	private DicePool getDicePoolFromResultSet(ResultSet rs) throws Exception {
		DicePool dicePool = new DicePool();
		dicePool.setIdDicePool(rs.getLong("idDicePool"));
		dicePool.setNumberOfDices(rs.getInt("numberOfDices"));
		dicePool.setNumberOfSides(rs.getInt("numberOfSides"));
		return dicePool;
	}
	
	private void logSQL(PreparedStatement pstmt) {
		String sql;
		if (pstmt == null)
			return;
		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);
	}

}