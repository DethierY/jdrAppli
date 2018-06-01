package jdr.appli.dao;

import jdr.appli.model.fonctional.CreationResponse;

public interface InsertOne<T> extends GetList<T> {
	
	public CreationResponse insertOne(T objectToInsert) throws Exception;

}
