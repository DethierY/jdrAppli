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

import jdr.appli.model.characterClass.Rank;

@Repository
public class RankDAO extends LogSQL implements GetOne<Rank> {
	
	private DataSource dataSource;
	
	@Autowired
	public RankDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}
	
	public Rank getOne(Long id) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		Rank rank = null;
		try {
			String sql = "SELECT * FROM rank WHERE idRank = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				rank = getRankFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return rank;
	}
	
	public List<Rank> getRanksForOneCharacterClass(Long id) throws Exception {
		Connection con = dataSource.getConnection();
		Rank rank;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Rank> aListOfRanks = new ArrayList<Rank>();
		try {
			sql = "SELECT * FROM rank WHERE characterClass = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL (pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rank = getRankFromResultSet(rs);
				aListOfRanks.add(rank);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return aListOfRanks;	
	}
	
	public Rank getRankFromResultSet(ResultSet rs) throws Exception {
		Rank rank = new Rank();
		rank.setIdRank(rs.getLong("idRank"));
		rank.setLevelValue(rs.getInt("levelValue"));
		rank.setRankValue(rs.getString("rankValue"));
		return rank;
	}

}
