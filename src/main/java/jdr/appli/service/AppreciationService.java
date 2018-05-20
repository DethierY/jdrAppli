package jdr.appli.service;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jdr.appli.dao.AppreciationDao;
import jdr.appli.model.Appreciation;

@Service
public class AppreciationService {
	
	private DataSource dataSource;
	
	@Autowired
	public AppreciationService(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}
	
	@Autowired
	private AppreciationDao dao;
	
	public Appreciation getOneAppreciation(Long id) throws Exception {
		Connection con = dataSource.getConnection();
		Appreciation appreciation = dao.getAppreciation(con, id);
		con.close();
		return appreciation;
	}

}
