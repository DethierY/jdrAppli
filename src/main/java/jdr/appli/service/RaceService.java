package jdr.appli.service;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jdr.appli.dao.RaceDao;
import jdr.appli.model.characterClass.Race;

@Service
public class RaceService {
	
	private DataSource dataSource;
	
	@Autowired
	public RaceService(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}
	
	@Autowired
	private RaceDao dao;
	
	public List<Race> getAllRaces() throws Exception {
		Connection con = dataSource.getConnection();
		List<Race> listRaces = dao.getListRaces(con);
		con.close();
		return listRaces;
	}
	
	public Race getOneRace(Long id) throws Exception {
		Connection con = dataSource.getConnection();
		Race race = dao.getRace(con, id);
		con.close();
		return race;
	}

}	
