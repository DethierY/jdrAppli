package jdr.appli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jdr.appli.model.score.User;

@Repository
public class UserDAO extends LogSQL implements GetOne<User> {
	
	private DataSource dataSource;
	
	@Autowired
	public UserDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}

	public User getOne(Long id) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		User user = null;
		try {
			String sql = "SELECT * FROM user WHERE idUser = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				user = getUserFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return user;
	}
	
	private User getUserFromResultSet(ResultSet rs) throws SQLException {
		User user = new User();
		user.setIdUser(rs.getLong("idUser"));
		user.setName(rs.getString("name"));
		return user;
	}
}
