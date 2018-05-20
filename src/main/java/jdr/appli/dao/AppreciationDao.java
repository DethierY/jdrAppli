package jdr.appli.dao;

import java.sql.Connection;

import jdr.appli.model.Appreciation;

public interface AppreciationDao {
	
	public Appreciation getAppreciation(Connection con, Long id) throws Exception;

}
