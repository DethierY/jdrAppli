package jdr.appli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jdr.appli.model.score.Appreciation;

@Repository
public class AppreciationDAO extends LogSQL implements GetOne<Appreciation> {

	private DataSource dataSource;
	
	@Autowired
	public AppreciationDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}
	
	public Appreciation getOne (Long id) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		Appreciation appreciation = null;
		try {
			String sql = "SELECT * FROM appreciation WHERE idAppreciation = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				appreciation = getAppreciationFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return appreciation;
	}
	
	private Appreciation getAppreciationFromResultSet(ResultSet rs) throws SQLException {
		Appreciation appreciation = new Appreciation();
		appreciation.setIdAppreciation(rs.getLong("idAppreciation"));
		appreciation.setAverageScore(rs.getDouble("averageScore"));
		appreciation.setNumberOfVoters(rs.getInt("numberOfVoters"));
		return appreciation;
	}
	
}
