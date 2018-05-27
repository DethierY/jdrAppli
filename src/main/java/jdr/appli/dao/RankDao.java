package jdr.appli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public Rank getRankFromResultSet(ResultSet rs) throws Exception {
		Rank rank = new Rank();
		rank.setIdRank(rs.getLong("idRank"));
		rank.setLevel1(rs.getString("level1"));
		rank.setLevel2(rs.getString("level2"));
		rank.setLevel3(rs.getString("level3"));
		rank.setLevel4(rs.getString("level4"));
		rank.setLevel5(rs.getString("level5"));
		rank.setLevel6(rs.getString("level6"));
		rank.setLevel7(rs.getString("level7"));
		rank.setLevel8(rs.getString("level8"));
		rank.setLevel9(rs.getString("level9"));
		rank.setLevel10(rs.getString("level10"));
		rank.setLevel11(rs.getString("level11"));
		rank.setLevel12(rs.getString("level12"));
		rank.setLevel13(rs.getString("level13"));
		rank.setLevel14(rs.getString("level14"));
		rank.setLevel15(rs.getString("level15"));
		rank.setLevel16(rs.getString("level16"));
		rank.setLevel17(rs.getString("level17"));
		rank.setLevel18(rs.getString("level18"));
		rank.setLevel19(rs.getString("level19"));
		rank.setLevel20(rs.getString("level20"));
		return rank;
	}

}
