package jdr.appli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdr.appli.dao.DAO;
import jdr.appli.model.characterClass.Race;

@Service
public class RaceService {
	
	@Autowired
	private DAO<Race> dao;
	
	public Race getOneRace(Long id) throws Exception {
		Race race = dao.getOne(id);
		return race;
	}
	
	public List<Race> getAllRaces() throws Exception {
		List<Race> listRaces = dao.getList();
		return listRaces;
	}
	
}	
