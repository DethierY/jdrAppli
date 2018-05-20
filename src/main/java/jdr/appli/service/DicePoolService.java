package jdr.appli.service;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jdr.appli.dao.DicePoolDao;
import jdr.appli.model.DicePool;

@Service
public class DicePoolService {
	
	private DataSource dataSource;
	
	@Autowired
	public DicePoolService(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}

	@Autowired
	private DicePoolDao dao;
	
	public DicePool getOneDicePool(Long id) throws Exception {
		Connection con = dataSource.getConnection();
		DicePool dicePool = dao.getDicePool(con, id);
		con.close();
		return dicePool;
	}
}	
