package jdr.appli.dao;

import jdr.appli.model.characterClass.User;

public interface UserDao {
	
	public User getUser(Long id) throws Exception;

}
