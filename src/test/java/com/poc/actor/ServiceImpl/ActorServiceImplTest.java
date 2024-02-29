package com.poc.actor.ServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.util.Arrays;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.poc.actor.dto.ActorDto;
import com.poc.actor.dto.SearchActorDto;
import com.poc.actor.entity.ActorEntity;
import com.poc.actor.entity.MovieEntity;
import com.poc.actor.repository.ActorRepository;
import com.poc.actor.util.JsonResponse;

@ExtendWith(SpringExtension.class)
class ActorServiceImplTest {
	
	@InjectMocks
	private ActorServiceImpl actorService;
	
	@Mock
	private ActorRepository actorRepo;
	
	@BeforeEach
	public void beforeTestSetup() {
		MovieEntity movieEntity1 = new MovieEntity();
		movieEntity1.setName("Animal");
		MovieEntity movieEntity2 = new MovieEntity();
		movieEntity2.setName("Tiger 3");
		ActorEntity actorEntity = new ActorEntity();
		actorEntity.setId(1000);
		actorEntity.setName("Salman");
		actorEntity.setMovieEntities(Arrays.asList(movieEntity1,movieEntity2));
		when(actorRepo.save(any(ActorEntity.class))).thenReturn(actorEntity);
		when(actorRepo.findByMobile(anyString())).thenReturn(actorEntity);
		when(actorRepo.findByName(anyString())).thenReturn(actorEntity);
	}
	
	@Test
	void findAllRecord_basicTest() {
		
		ActorEntity save = actorRepo.save(new ActorEntity());
		ResponseEntity<JsonResponse> saveActor = actorService.saveActor(new ActorDto());
		
		assertEquals("Salman", save.getName());
		assertEquals(201, saveActor.getStatusCodeValue());
	}
	
	@Test
	void fetchMoviesList_basicTest() throws JSONException {
		SearchActorDto searchActorDto = new SearchActorDto();
		searchActorDto.setMobile("9876543210");
		ResponseEntity<JsonResponse> saveActor = actorService.fetchMoviesList(searchActorDto);
		String expected = "ActorDto(id=1000, name=Salman, mobile=null, emailId=null, dob=null, fee=0.0, isAvailable=false, movieDto=[MovieDto(id=null, name=Animal, directorName=null, releaseYear=0, status=null, actorDto=null), MovieDto(id=null, name=Tiger 3, directorName=null, releaseYear=0, status=null, actorDto=null)])";
		
//		jsonPath("$.statusCode", is(200));
//		jsonPath("$.payload", notNullValue());
//		jsonPath("$.payload.name", is("hjcj"));
		
		assertEquals(200, saveActor.getStatusCodeValue());
		assertEquals(expected, saveActor.getBody().getPayload().toString());
	}

}
