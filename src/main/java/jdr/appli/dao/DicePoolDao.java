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

import jdr.appli.model.DicePool;

@Repository
public class DicePoolDAO extends DAO<DicePool> {

	private DataSource dataSource;
	
	@Autowired
	public DicePoolDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}
	
	public DicePool getOne(Long id) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		DicePool dicePool = null;
		try {
			String sql = "SELECT * FROM dicepool WHERE idDicePool = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				dicePool = getDicePoolFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return dicePool;
	}
	
	public List<DicePool> getList() throws Exception {
		Connection con = dataSource.getConnection();
		DicePool dicePool;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<DicePool> aListOfDicePools = new ArrayList<DicePool>();
		try {
			sql = "SELECT * FROM characterclass";
			pstmt = con.prepareStatement(sql);
			logSQL (pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dicePool = getDicePoolFromResultSet(rs);
				aListOfDicePools.add(dicePool);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL error !:" + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return aListOfDicePools;
	}
	
	private DicePool getDicePoolFromResultSet(ResultSet rs) throws SQLException {
		DicePool dicePool = new DicePool();
		dicePool.setIdDicePool(rs.getLong("idDicePool"));
		dicePool.setNumberOfDice(rs.getInt("numberOfDice"));
		dicePool.setNumberOfSides(rs.getInt("numberOfSides"));
		return dicePool;
	}

}
