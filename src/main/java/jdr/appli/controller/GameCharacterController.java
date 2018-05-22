package jdr.appli.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jdr.appli.model.CreationResponse;
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
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listGameCharacters);
	}
	
	@GetMapping(value = "/list/{id}")
	public ResponseEntity<?> getAlluserGameCharacters(@PathVariable Long id){
		List<GameCharacter> listUserGameCharacters = null;
		try {
			listUserGameCharacters = gameCharacterService.getAllUserGameCharacters(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listUserGameCharacters);
	}
	
	@PostMapping(value ="/create")
	public ResponseEntity<?> addGameCharacter (@RequestBody GameCharacter gameCharacter){
		CreationResponse response;
		try {
			response = gameCharacterService.addGameCharacter(gameCharacter);
			return ResponseEntity.status(response.getStatus()).body(response.getMessage());	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}
}
