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

import jdr.appli.model.characterClass.CharacterClass;
import jdr.appli.model.characterClass.DicePool;
import jdr.appli.model.characterClass.LevelBonus;
import jdr.appli.model.characterClass.Race;
import jdr.appli.model.characterClass.Rank;

@Repository
public class CharacterClassDAO extends LogSQL implements GetList<CharacterClass> {
	
	private DataSource dataSource;
	
	@Autowired
	private GetOne<Race> raceDAO;
	
	@Autowired
	private GetOne<DicePool> dicePoolDAO;
	
	@Autowired
	private GetOne<LevelBonus> levelBonusDAO;
	
	@Autowired
	private GetOne<Rank> rankDAO;
	
	@Autowired
	public CharacterClassDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}
	
	public CharacterClass getOne(Long id) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		CharacterClass characterClass = null;
		try {
			String sql = "SELECT * FROM characterclass WHERE idCharacterClass = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				characterClass = getCharacterClassFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return characterClass;
	}
	
	public List<CharacterClass> getList() throws Exception {
		Connection con = dataSource.getConnection();
		CharacterClass characterClass;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<CharacterClass> aListOfCharacterClasses = new ArrayList<CharacterClass>();
		try {
			sql = "SELECT * FROM characterclass";
			pstmt = con.prepareStatement(sql);
			logSQL (pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				characterClass = getCharacterClassFromResultSet(rs);
				aListOfCharacterClasses.add(characterClass);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL error !:" + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return aListOfCharacterClasses;
	}
	
	private CharacterClass getCharacterClassFromResultSet(ResultSet rs) throws Exception {
		CharacterClass characterClass = new CharacterClass();
		characterClass.setIdCharacterClass(rs.getLong("idCharacterClass"));
		characterClass.setClassName(rs.getString("className"));
		characterClass.setRace(raceDAO.getOne(rs.getLong("race")));
		characterClass.setStartingAge(rs.getInt("startingAge"));
		characterClass.setStartingAgeModifier(dicePoolDAO.getOne(rs.getLong("startingAgeModifier")));
		characterClass.setFortitudeSave(levelBonusDAO.getOne(rs.getLong("fortitudeSave")));
		characterClass.setReflexSave(levelBonusDAO.getOne(rs.getLong("reflexSave")));
		characterClass.setWillSave(levelBonusDAO.getOne(rs.getLong("willSave")));
		characterClass.setEnduranceDie(dicePoolDAO.getOne(rs.getLong("enduranceDie")));
		characterClass.setStartingWealth(rs.getInt("startingWealth"));
		characterClass.setWealthModifier(dicePoolDAO.getOne(rs.getLong("wealthModifier")));
		characterClass.setRank(rankDAO.getOne(rs.getLong("rank")));
		return characterClass;
	}

}
