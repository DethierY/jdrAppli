package jdr.appli.service;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jdr.appli.dao.LevelBonusDao;
import jdr.appli.model.characterClass.LevelBonus;

@Service
public class LevelBonusService {
	
	private DataSource dataSource;
	
	@Autowired
	public LevelBonusService(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}

	@Autowired
	private LevelBonusDao dao;
	
	public LevelBonus getOneLevelBonus(Long id) throws Exception {
		Connection con = dataSource.getConnection();
		LevelBonus levelBonus = dao.getLevelBonus(con, id);
		con.close();
		return levelBonus;
	}
}
