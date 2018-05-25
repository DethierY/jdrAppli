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

import jdr.appli.model.characterClass.LevelBonus;

@Repository
public class LevelBonusDAO extends DAO<LevelBonus> {

	private DataSource dataSource;
	
	@Autowired
	public LevelBonusDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}
	
	@Override
	public LevelBonus getOne(Long id) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		LevelBonus levelBonus = null;
		try {
			String sql = "SELECT * FROM levelbonus WHERE idLevelBonus = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				levelBonus = getLevelBonusFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return levelBonus;
	}
	
	public List<LevelBonus> getList() throws Exception {
		Connection con = dataSource.getConnection();
		LevelBonus levelBonus;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<LevelBonus> aListOfLevelBonuses = new ArrayList<LevelBonus>();
		try {
			sql = "SELECT * FROM characterclass";
			pstmt = con.prepareStatement(sql);
			logSQL (pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				levelBonus = getLevelBonusFromResultSet(rs);
				aListOfLevelBonuses.add(levelBonus);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL error !:" + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return aListOfLevelBonuses;
	}

	private LevelBonus getLevelBonusFromResultSet(ResultSet rs) throws Exception {
		LevelBonus levelBonus = new LevelBonus();
		levelBonus.setIdLevelBonus(rs.getLong("idLevelBonus"));
		levelBonus.setLevel1(rs.getInt("level1"));
		levelBonus.setLevel2(rs.getInt("level2"));
		levelBonus.setLevel3(rs.getInt("level3"));
		levelBonus.setLevel4(rs.getInt("level4"));
		levelBonus.setLevel5(rs.getInt("level5"));
		levelBonus.setLevel6(rs.getInt("level6"));
		levelBonus.setLevel7(rs.getInt("level7"));
		levelBonus.setLevel8(rs.getInt("level8"));
		levelBonus.setLevel9(rs.getInt("level9"));
		levelBonus.setLevel10(rs.getInt("level10"));
		levelBonus.setLevel11(rs.getInt("level11"));
		levelBonus.setLevel12(rs.getInt("level12"));
		levelBonus.setLevel13(rs.getInt("level13"));
		levelBonus.setLevel14(rs.getInt("level14"));
		levelBonus.setLevel15(rs.getInt("level15"));
		levelBonus.setLevel16(rs.getInt("level16"));
		levelBonus.setLevel17(rs.getInt("level17"));
		levelBonus.setLevel18(rs.getInt("level18"));
		levelBonus.setLevel19(rs.getInt("level19"));
		levelBonus.setLevel20(rs.getInt("level20"));
		return levelBonus;
	}
}
