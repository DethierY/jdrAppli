package jdr.appli.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import jdr.appli.model.characterClass.User;

public class UserResultSet implements ObjectResultSet {

	@Override
	public Object getObjectFromResultSet(ResultSet rs) throws SQLException {
		User user = new User();
		user.setIdUser(rs.getLong("idUser"));
		user.setName(rs.getString("name"));
		user.setLogin(rs.getString("login"));
		return user;
	}
}
