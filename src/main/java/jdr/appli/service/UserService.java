package jdr.appli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdr.appli.dao.UserDao;
import jdr.appli.model.User;

@Service
public class UserService {

	@Autowired
	private UserDao dao;
	
	public User getOneUser(String login) throws Exception {
		return dao.getUser(login);
	}
}
