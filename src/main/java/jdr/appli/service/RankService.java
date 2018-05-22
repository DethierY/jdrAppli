package jdr.appli.service;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jdr.appli.dao.RankDao;
import jdr.appli.model.characterClass.Rank;

@Service
public class RankService {

	private DataSource dataSource;
	
	@Autowired
	public RankService(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}

	@Autowired
	private RankDao dao;
	
	public Rank getOneRank(Long id) throws Exception {
		Connection con = dataSource.getConnection();
		Rank rank = dao.getRank(con, id);
		con.close();
		return rank;
	}
}
