package jdr.appli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import jdr.appli.model.characterClass.Rank;

@Repository
public class RankDaoJdbc extends LogSQL implements RankDao {
	
	ResultSetContext rsContext;
	
	@Override
	public Rank getRank(Connection con, Long id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		Rank rank = null;
		try {
			String sql = "SELECT * FROM rank WHERE idRank = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			rsContext = new ResultSetContext(new RankResultSet());
			if (rs.next())
				rank = (Rank) rsContext.executeResultSetStrategy(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
		}
		return rank;
	}

}
