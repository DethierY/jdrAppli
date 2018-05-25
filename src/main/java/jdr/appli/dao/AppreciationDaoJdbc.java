package jdr.appli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import jdr.appli.model.Appreciation;

@Repository
public class AppreciationDaoJdbc extends LogSQL implements AppreciationDao {

	ResultSetContext rsContext;
	
	@Override
	public Appreciation getAppreciation (Connection con, Long id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		Appreciation appreciation = null;
		try {
			String sql = "SELECT * FROM appreciation WHERE idAppreciation = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			rsContext = new ResultSetContext(new AppreciationResultSet());
			if (rs.next())
				appreciation = (Appreciation) rsContext.executeResultSetStrategy(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
		}
		return appreciation;
	}

}
