package jdr.appli.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jdr.appli.model.Name;
import jdr.appli.service.CharacterClassService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
@RestController
@RequestMapping("/class")
public class CharacterClassController {
	
	@Autowired
	private CharacterClassService characterClassService;
	
	@GetMapping(value = "/names")
	public ResponseEntity<?> getAllClassNames(){
		List<Name> listClassNames = null;
		try {
			listClassNames = characterClassService.getAllClassNames();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listClassNames);
	}	
	

}
