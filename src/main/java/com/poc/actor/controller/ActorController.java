package com.poc.actor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc.actor.dto.ActorDto;
import com.poc.actor.dto.SearchActorDto;
import com.poc.actor.service.ActorService;
import com.poc.actor.util.JsonResponse;

@RestController
@RequestMapping("/v1/api/public")
@CrossOrigin(origins="http://localhost:4200")
public class ActorController {
	
	@Autowired
	private ActorService actorService;
	
//	@GetMapping("/getAllRecord")
//	public ResponseEntity<JsonResponse> getActor() {
//		return actorService.findAllRecord();
//	}
	
	@PostMapping("/addActor")
	public ResponseEntity<JsonResponse> addActor(@RequestBody ActorDto actorDto){
		return actorService.saveActor(actorDto);
	}
	
	@GetMapping("/getActorList")
	public ResponseEntity<List<String>> actorList(){
		return actorService.getActorList();
	}
	
//	@GetMapping("/getActorList")
//	public ResponseEntity<JsonResponse> actorList(){
//		return actorService.getActorList();
//	}
	
	@GetMapping("/getMovieList")
	public ResponseEntity<JsonResponse> getMovieList(@RequestParam("name") String name, @RequestParam("mobile") String mobile) {
		SearchActorDto searchActor = new SearchActorDto();
		searchActor.setMobile(mobile);
		searchActor.setName(name);
		return actorService.fetchMoviesList(searchActor);
	}
	
	@DeleteMapping("/actor")
	public ResponseEntity<?> deleteActor(){
		return null;
	}

}
