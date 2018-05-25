package jdr.appli.dao;

import java.sql.ResultSet;

public class ResultSetContext {
	
	private ObjectResultSet objectResultSet;
	
	public ResultSetContext(ObjectResultSet objectResultSet) {
		this.objectResultSet = objectResultSet;
	}

	public Object executeResultSetStrategy(ResultSet rs) throws Exception {
		return objectResultSet.getObjectFromResultSet(rs);
	}
}
