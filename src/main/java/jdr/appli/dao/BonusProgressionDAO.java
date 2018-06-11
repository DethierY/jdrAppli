package jdr.appli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jdr.appli.model.characterClass.BonusProgression;

@Repository
public class BonusProgressionDAO extends LogSQL implements GetOne<BonusProgression>{
		
	private DataSource dataSource;
	
	@Autowired
	private BonusDAO bonusDAO;
	
	@Autowired
	public BonusProgressionDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}
	
	public BonusProgression getOne(Long id) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		BonusProgression bonusProgression = null;
		try {
			String sql = "SELECT * FROM bonusProgression WHERE idBonusProgression = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				bonusProgression = getBonusProgressionFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return bonusProgression;
	}
	
	private BonusProgression getBonusProgressionFromResultSet(ResultSet rs) throws Exception {
		BonusProgression bonusProgression = new BonusProgression();
		bonusProgression.setIdBonusProgression(rs.getLong("idBonusProgression"));
		bonusProgression.setProgressionValue(bonusDAO.getLevelBonusList(rs.getLong("idBonusProgression")));
		return bonusProgression;
	}
}
