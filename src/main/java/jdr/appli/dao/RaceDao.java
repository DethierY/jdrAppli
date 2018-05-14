package jdr.appli.dao;

import java.util.List;

import jdr.appli.model.characterClass.Race;

public interface RaceDao {

	public List<Race> getListRaces() throws Exception;
	
	public Race getRace(Long id) throws Exception;
}
