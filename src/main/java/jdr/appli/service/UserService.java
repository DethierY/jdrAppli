package jdr.appli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdr.appli.dao.DAO;
import jdr.appli.model.characterClass.User;

@Service
public class UserService {

	@Autowired
	private DAO<User> dao;
	
	public User getOneUser(Long id) throws Exception {
		User user = dao.getOne(id);
		return user;
	}
}
