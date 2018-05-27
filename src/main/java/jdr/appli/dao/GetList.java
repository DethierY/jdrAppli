package jdr.appli.dao;

import java.util.List;

public interface GetList<T> extends GetOne<T> {

	public List<T> getList() throws Exception;
}
