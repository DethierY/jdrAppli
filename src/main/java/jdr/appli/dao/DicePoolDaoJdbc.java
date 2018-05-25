package jdr.appli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import jdr.appli.model.DicePool;

@Repository
public class DicePoolDaoJdbc extends LogSQL implements DicePoolDao {

	ResultSetContext rsContext;
	
	@Override
	public DicePool getDicePool(Connection con, Long id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		DicePool dicePool = null;
		try {
			String sql = "SELECT * FROM dicepool WHERE idDicePool = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			rsContext = new ResultSetContext(new DicePoolResultSet());
			if (rs.next())
				dicePool = (DicePool) rsContext.executeResultSetStrategy(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
		}
		return dicePool;
	}

}
