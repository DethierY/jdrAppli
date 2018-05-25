package jdr.appli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import jdr.appli.model.characterClass.LevelBonus;

@Repository
public class LevelBonusDaoJdbc extends LogSQL implements LevelBonusDao {

	ResultSetContext rsContext;
	
	@Override
	public LevelBonus getLevelBonus(Connection con, Long id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		LevelBonus levelBonus = null;
		try {
			String sql = "SELECT * FROM levelbonus WHERE idLevelBonus = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			rsContext = new ResultSetContext(new LevelBonusResultSet());
			if (rs.next())
				levelBonus = (LevelBonus) rsContext.executeResultSetStrategy(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
		}
		return levelBonus;
	}

}
