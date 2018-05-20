package jdr.appli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jdr.appli.model.characterClass.CharacterClass;
import jdr.appli.service.DicePoolService;
import jdr.appli.service.LevelBonusService;
import jdr.appli.service.RaceService;

@Repository
public class CharacterClassDaoJdbc extends LogSQL implements CharacterClassDao {
	
	@Autowired
	private RaceService raceService;
	
	@Autowired
	private DicePoolService dicePoolService;
	
	@Autowired
	private LevelBonusService levelBonusService;
	
	@Override
	public List<CharacterClass> getListCharacterClasses(Connection con) throws Exception {
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
		}
		return aListOfCharacterClasses;
	}
	
	@Override
	public CharacterClass getCharacterClass(Connection con, Long id) throws Exception {
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
		}
		return characterClass;
	}
	
	private CharacterClass getCharacterClassFromResultSet(ResultSet rs) throws Exception {
		CharacterClass characterClass = new CharacterClass();
		characterClass.setIdCharacterClass(rs.getLong("idCharacterClass"));
		characterClass.setClassName(rs.getString("className"));
		characterClass.setRace(raceService.getOneRace(rs.getLong("race")));
		characterClass.setStartingAge(rs.getInt("startingAge"));
		characterClass.setStartingAgeModifier(dicePoolService.getOneDicePool(rs.getLong("startingAgeModifier")));
		characterClass.setFortitudeSave(levelBonusService.getOneLevelBonus(rs.getLong("fortitudeSave")));
		characterClass.setReflexSave(levelBonusService.getOneLevelBonus(rs.getLong("reflexSave")));
		characterClass.setWillSave(levelBonusService.getOneLevelBonus(rs.getLong("willSave")));
		return characterClass;
	}

}
