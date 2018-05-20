package jdr.appli.service;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jdr.appli.dao.UserDao;
import jdr.appli.model.characterClass.User;

@Service
public class UserService {
	
	private DataSource dataSource;
	
	@Autowired
	public UserService(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}

	@Autowired
	private UserDao dao;
	
	public User getOneUser(Long id) throws Exception {
		Connection con = dataSource.getConnection();
		User user = dao.getUser(con, id);
		con.close();
		return user;
	}
}
