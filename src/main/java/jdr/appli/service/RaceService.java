package jdr.appli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdr.appli.dao.RaceDao;
import jdr.appli.model.characterClass.Race;

@Service
public class RaceService {
	
	@Autowired
	private RaceDao dao;
	
	public List<Race> getAllRaces() throws Exception {
		return dao.getListRaces();
	}

}	
