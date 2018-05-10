package jdr.appli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdr.appli.dao.CharacterClassDao;
import jdr.appli.model.Name;

@Service
public class CharacterClassService {
	
	@Autowired
	private CharacterClassDao dao;
	
	public List<Name> getAllClassNames() throws Exception {
		return dao.getListClassNames();
	}

}
