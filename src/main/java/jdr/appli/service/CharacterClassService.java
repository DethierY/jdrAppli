package jdr.appli.service;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jdr.appli.dao.CharacterClassDao;
import jdr.appli.model.characterClass.CharacterClass;

@Service
public class CharacterClassService {
	
	private DataSource dataSource;
	
	@Autowired
	public CharacterClassService(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}
	
	@Autowired
	private CharacterClassDao dao;
	
	public List<CharacterClass> getAllCharacterClasses() throws Exception {
		Connection con = dataSource.getConnection();
		List<CharacterClass> listCharacterClasses = dao.getListCharacterClasses(con);
		con.close();
		return listCharacterClasses;
	}
	
	public CharacterClass getOneCharacterClass(Long id) throws Exception {
		Connection con = dataSource.getConnection();
		CharacterClass characterClass = dao.getCharacterClass(con, id);
		con.close();
		return characterClass;
	}

}
