package jdr.appli.dao;

import java.sql.Connection;

import jdr.appli.model.characterClass.Rank;

public interface RankDao {

	public Rank getRank(Connection con, Long id) throws Exception;
}
