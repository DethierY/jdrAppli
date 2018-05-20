package jdr.appli.dao;

import java.sql.Connection;
import java.util.List;

import jdr.appli.model.characterClass.Race;

public interface RaceDao {

	public List<Race> getListRaces(Connection con) throws Exception;
	
	public Race getRace(Connection con, Long id) throws Exception;
}
