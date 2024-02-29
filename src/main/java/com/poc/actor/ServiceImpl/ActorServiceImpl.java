package com.poc.actor.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.poc.actor.dto.ActorDto;
import com.poc.actor.dto.MovieDto;
import com.poc.actor.dto.SearchActorDto;
import com.poc.actor.entity.ActorEntity;
import com.poc.actor.entity.MovieEntity;
import com.poc.actor.mapper.ActorMapper;
import com.poc.actor.mapper.MovieMapper;
import com.poc.actor.repository.ActorRepository;
import com.poc.actor.service.ActorService;
import com.poc.actor.util.JsonResponse;

@Component
public class ActorServiceImpl implements ActorService {
	
	public static final Logger logger = LoggerFactory.getLogger(ActorServiceImpl.class);
	
	@Autowired
	private ActorRepository actorRepo;
	
	@Override
	public ResponseEntity<JsonResponse> findAllRecord() {
		JsonResponse jsonResponse = new JsonResponse();
		Optional<ActorEntity> actorEntity = Optional.ofNullable(new ActorEntity());
		
		ActorDto actorDto = new ActorDto();
		new MovieDto();
		List<MovieDto> movies = new ArrayList<>();
		
		try {
			actorEntity = actorRepo.findById(1010);
			if(actorEntity.isPresent()) {
				actorDto = ActorMapper.copyPropertiesFromEntity(actorEntity.get());
				for(MovieEntity movie: actorEntity.get().getMovieEntities()) {
					movies.add(MovieMapper.copyPropertiesFromEntity(movie));
				}
				actorDto.setMovieDto(movies);
			}
					
			jsonResponse.setPayload(actorDto);
			jsonResponse.setMessage("Data Fetch Success");
		}catch(Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			jsonResponse.setStatus("Failed");
			jsonResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
			jsonResponse.setMessage("Failed while fetching movie details");
			return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.EXPECTATION_FAILED);
		}
		System.out.println(jsonResponse);
		return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<JsonResponse> saveActor(ActorDto actorDto){
		
		JsonResponse jsonResponse = new JsonResponse();
		new ArrayList<>();
		new MovieEntity();
		ActorEntity actorEntity = new ActorEntity();
		
		try {
			logger.info("Inside saveActor() ---------------->");
			logger.info(" Received Data   ----------------> " + actorDto.toString());
			
			actorEntity = ActorMapper.copyPropertiesFromDto(actorDto);
			actorRepo.save(actorEntity);
			
			jsonResponse.setMessage("Data Saved in DB");
			jsonResponse.setStatusCode(HttpStatus.CREATED.value());
			logger.info("Inside saveActor() ----------------> Data Saved in DB");
			
		}catch(Exception ex) {
			logger.info("Inside saveActor() ---------------->Error! Data not Saved in DB");
			logger.info(ex.getLocalizedMessage());
			jsonResponse.setStatus("Failed");
			jsonResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
			jsonResponse.setMessage("Data not inserted in DB");
			logger.info(jsonResponse.toString());
			return  new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.CREATED);
	}
	
	public  ResponseEntity<List<String>> getActorList(){
		JsonResponse jsonResponse = new JsonResponse();
		List<String> actorNameList = new ArrayList<>();
		logger.info("Inside getActorList() ---------------->");
		
		actorNameList = actorRepo.findName();
		jsonResponse.setMessage("Actor Name List fetched from DB");
		jsonResponse.setPayload(actorNameList);
		jsonResponse.setStatusCode(HttpStatus.OK.value());
		
		logger.info("Inside saveActor() ----------------> Actor List fetched from  DB");
//		return new  ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
		
		return new  ResponseEntity<List<String>>(actorNameList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<JsonResponse> fetchMoviesList(SearchActorDto searchActor) {
		// TODO Auto-generated method stub
		
		JsonResponse jsonResponse = new JsonResponse();
		ActorEntity actorEntity = new ActorEntity();
		ActorDto actorDto = new ActorDto();
		
		logger.info("Inside fetchMoviesList() ---------------->");
		try {
			if(searchActor.getMobile() != null && !searchActor.getMobile().isEmpty() 
					&& searchActor.getMobile().length() == 10) {
				actorEntity = actorRepo.findByMobile(searchActor.getMobile());
				
				
			}else if(searchActor.getName() != null && !searchActor.getName().isEmpty() && searchActor.getName() != "") {
				actorEntity = actorRepo.findByName(searchActor.getName());
			}
			
			if(actorEntity != null) {
				actorDto = ActorMapper.copyPropertiesFromEntity(actorEntity);
				jsonResponse.setPayload(actorDto);
				jsonResponse.setMessage("Movie List fetched");
			}else {
				jsonResponse.setMessage("Actor not Found!!");
			}
			jsonResponse.setStatusCode(HttpStatus.OK.value());
		}catch(Exception ex) {
			logger.info("Inside fetchMoviesList() ----------------> Error! While retreving movies list");
			logger.info(ex.getLocalizedMessage());
			jsonResponse.setMessage("Something went wrong while retriving movie details");
			jsonResponse.setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
			return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
	}
}
