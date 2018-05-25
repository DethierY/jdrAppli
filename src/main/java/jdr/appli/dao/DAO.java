package jdr.appli.dao;

import java.util.List;

public abstract class DAO<T> extends LogSQL{

	public abstract T getOne(Long id) throws Exception;
	
	public abstract List<T> getList() throws Exception;
	
}
