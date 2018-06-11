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

import jdr.appli.model.characterClass.Bonus;

@Repository
public class BonusDAO extends LogSQL implements GetOne<Bonus> {

private DataSource dataSource;
	
	@Autowired
	public BonusDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}
	
	public Bonus getOne(Long id) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		Bonus bonus = null;
		try {
			String sql = "SELECT * FROM bonus WHERE idBonus = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				bonus = getBonusFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return bonus;
	}
	
	public List<Bonus> getLevelBonusList(Long id) throws Exception {
		Connection con = dataSource.getConnection();
		Bonus bonus;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Bonus> aListOfBonuses = new ArrayList<Bonus>();
		try {
			sql = "SELECT bonus.idBonus, bonus.levelValue, bonus.bonusValue FROM bonus"
					+ " JOIN bonusprogression ON bonus.bonusProgression = bonusprogression.idBonusProgression"
					+ " WHERE bonusprogression.idBonusProgression = ?"
					+ " ORDER BY bonus.levelValue";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL (pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bonus = getBonusFromResultSet(rs);
				aListOfBonuses.add(bonus);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL error !:" + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return aListOfBonuses;
	}

	private Bonus getBonusFromResultSet(ResultSet rs) throws Exception {
		Bonus bonus = new Bonus();
		bonus.setIdBonus(rs.getLong("idBonus"));
		bonus.setLevelValue(rs.getInt("levelValue"));
		bonus.setBonusValue(rs.getInt("bonusValue"));
		return bonus;
	}
	
}
