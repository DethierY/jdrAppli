package jdr.appli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jdr.appli.model.characterClass.DicePool;
import jdr.appli.model.characterClass.Race;

@Repository
public class RaceDAO extends LogSQL implements GetList<Race> {
	
	private DataSource dataSource;
	
	@Autowired
	private GetOne<DicePool> dicePoolDAO;
	
	@Autowired
	public RaceDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}
	
	public Race getOne(Long id) throws Exception {
		Connection con = dataSource.getConnection();
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
	
	public List<Race> getList() throws Exception {
		Connection con = dataSource.getConnection();
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
	
	public Race getRaceFromResultSet(ResultSet rs) throws Exception {
		Race race = new Race();
		race.setIdRace(rs.getLong("idRace"));
		race.setRaceName(rs.getString("raceName"));
		race.setBaseHeight(rs.getDouble("baseHeight"));
		race.setHeightSexModifier(rs.getDouble("heightSexModifier"));
		race.setHeightModifier(dicePoolDAO.getOne(rs.getLong("heightModifier")));
		race.setBaseWeight(rs.getInt("baseWeight"));
		race.setWeightSexModifier(rs.getInt("weightSexModifier"));
		race.setWeightModifier(dicePoolDAO.getOne(rs.getLong("weightModifier")));
		return race;
	}
	
}
