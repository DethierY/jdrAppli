package jdr.appli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAO extends LogSQL {
	
	private DataSource dataSource;
	
	@Autowired
	public TestDAO (JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}
	
	public List<Integer> getBonusList (Long id) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		int bonusValue;
		ArrayList<Integer> bonusValues = new ArrayList<Integer>();;
		try {
			String sql = "SELECT bonus.bonusValue FROM bonus"
					+ " JOIN bonusprogression ON bonus.bonusprogression = bonusprogression.idBonusProgression"
					+ " JOIN characterclass ON bonusprogression.idBonusProgression = characterclass.willSaveBonus"
					+ " WHERE characterclass.idCharacterClass = ?"
					+ " ORDER BY bonus.levelValue ASC";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bonusValue = getBonusFromResultSet(rs);
				bonusValues.add(bonusValue);
			}	
		} catch (Exception e) {
			e.printStackTrace();;
			log.error("SQL Error !:" + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return bonusValues;
	}

	private int getBonusFromResultSet(ResultSet rs) throws Exception {
		int bonus;
		bonus = rs.getInt("bonusValue");
		return bonus;
	}
	
}
