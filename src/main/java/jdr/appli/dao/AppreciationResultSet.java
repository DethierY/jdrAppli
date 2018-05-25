package jdr.appli.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import jdr.appli.model.Appreciation;

public class AppreciationResultSet implements ObjectResultSet {
	
	@Override
	public Appreciation getObjectFromResultSet(ResultSet rs) throws SQLException {
		Appreciation appreciation = new Appreciation();
		appreciation.setIdAppreciation(rs.getLong("idAppreciation"));
		appreciation.setAverageScore(rs.getDouble("averageScore"));
		appreciation.setNumberOfVoters(rs.getInt("numberOfVoters"));
		return appreciation;
	}

}
