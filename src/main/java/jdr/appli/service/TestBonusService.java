package jdr.appli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdr.appli.dao.TestDAO;

@Service
public class TestBonusService {

	@Autowired
	private TestDAO dao;
	
	public List<Integer> getBonusList (Long id) throws Exception {
		List<Integer> bonusValues = dao.getBonusList(id);
		return bonusValues;
	}
}
