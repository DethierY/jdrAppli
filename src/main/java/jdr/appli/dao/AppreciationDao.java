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

import jdr.appli.model.Appreciation;

@Repository
public class AppreciationDAO extends DAO<Appreciation> {

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

	public List<Appreciation> getList() throws Exception {
		Connection con = dataSource.getConnection();
		Appreciation appreciation;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Appreciation> aListOfAppreciations = new ArrayList<Appreciation>();
		try {
			sql = "SELECT * FROM characterclass";
			pstmt = con.prepareStatement(sql);
			logSQL (pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				appreciation = getAppreciationFromResultSet(rs);
				aListOfAppreciations.add(appreciation);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL error !:" + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return aListOfAppreciations;
	}
	
	private Appreciation getAppreciationFromResultSet(ResultSet rs) throws SQLException {
		Appreciation appreciation = new Appreciation();
		appreciation.setIdAppreciation(rs.getLong("idAppreciation"));
		appreciation.setAverageScore(rs.getDouble("averageScore"));
		appreciation.setNumberOfVoters(rs.getInt("numberOfVoters"));
		return appreciation;
	}
	
}
