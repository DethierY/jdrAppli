package jdr.appli.dao;

import java.sql.Connection;

import jdr.appli.model.characterClass.User;

public interface UserDao {
	
	public User getUser(Connection con, Long id) throws Exception;

}
