package jdr.appli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdr.appli.dao.DicePoolDao;
import jdr.appli.model.DicePool;

@Service
public class DicePoolService {

	@Autowired
	private DicePoolDao dao;
	
	public DicePool getOneDicePool(Long id) throws Exception {
		return dao.getDicePool(id);
	}
}	
