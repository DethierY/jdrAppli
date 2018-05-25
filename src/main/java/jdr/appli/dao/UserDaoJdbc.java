package jdr.appli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import jdr.appli.model.characterClass.User;

@Repository
public class UserDaoJdbc extends LogSQL implements UserDao {
	
	ResultSetContext rsContext;

	@Override
	public User getUser(Connection con, Long id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		User user = null;
		try {
			String sql = "SELECT * FROM user WHERE idUser = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			rsContext = new ResultSetContext(new UserResultSet());
			if (rs.next())
				user = (User) rsContext.executeResultSetStrategy(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
		}
		return user;
	}

}
