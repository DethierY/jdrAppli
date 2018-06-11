package jdr.appli.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jdr.appli.service.TestBonusService;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestBonusService testService;
	
	@GetMapping(value = "/list/{id}")
	public ResponseEntity<?> getBonusList(@PathVariable Long id) {
		List<Integer> listBonus = null;
		try {
			listBonus = testService.getBonusList(id);
			return ResponseEntity.status(HttpStatus.OK).body(listBonus);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
