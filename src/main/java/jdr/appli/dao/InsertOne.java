package jdr.appli.dao;

import org.springframework.http.ResponseEntity;

public interface InsertOne<T> extends GetList<T> {
	
	public ResponseEntity<?> insertOne(T objectToInsert) throws Exception;

}
