package jdr.appli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdr.appli.dao.AppreciationDao;
import jdr.appli.model.Appreciation;

@Service
public class AppreciationService {
	
	@Autowired
	private AppreciationDao dao;
	
	public Appreciation getOneAppreciation(Long id) throws Exception {
		return dao.getAppreciation(id);
	}

}
