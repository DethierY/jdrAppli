package jdr.appli.dao;

import java.sql.ResultSet;

public interface ObjectResultSet {
	
	public Object getObjectFromResultSet(ResultSet rs) throws Exception;

}
