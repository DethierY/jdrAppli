package jdr.appli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	private Bonus getBonusFromResultSet(ResultSet rs) throws Exception {
		Bonus bonus = new Bonus();
		bonus.setIdBonus(rs.getLong("idBonus"));
		bonus.setLevelValue(rs.getInt("levelValue"));
		bonus.setBonusValue(rs.getInt("bonusValue"));
		return bonus;
	}
	
}
