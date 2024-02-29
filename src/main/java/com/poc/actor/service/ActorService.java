package com.poc.actor.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.poc.actor.dto.ActorDto;
import com.poc.actor.dto.SearchActorDto;
import com.poc.actor.util.JsonResponse;

@Service
public interface ActorService {

	public ResponseEntity<JsonResponse> findAllRecord();

	public ResponseEntity<JsonResponse> saveActor(ActorDto actorDto);

	public ResponseEntity<List<String>> getActorList();

	public ResponseEntity<JsonResponse> fetchMoviesList(SearchActorDto searchActor);

}
