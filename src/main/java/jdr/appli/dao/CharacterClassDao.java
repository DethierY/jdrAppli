package jdr.appli.dao;

import java.util.List;

import jdr.appli.model.Name;

public interface CharacterClassDao {
	
	public List<Name> getListClassNames() throws Exception;

}
