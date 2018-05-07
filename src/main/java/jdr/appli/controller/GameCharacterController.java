package jdr.appli.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jdr.appli.model.GameCharacter;
import jdr.appli.service.GameCharacterService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
@RestController
@RequestMapping("/character")
public class GameCharacterController {

	@Autowired
	private GameCharacterService gameCharacterService;
	
	@GetMapping(value = "/list")
	public ResponseEntity<?> getAllGameCharacters(){
		List<GameCharacter> listGameCharacters = null;
		try {
			listGameCharacters = gameCharacterService.getAllGameCharacters();
			System.out.println(listGameCharacters);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listGameCharacters);
	}
}
