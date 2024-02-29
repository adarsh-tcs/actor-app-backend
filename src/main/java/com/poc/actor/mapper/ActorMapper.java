package com.poc.actor.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.poc.actor.dto.ActorDto;
import com.poc.actor.dto.MovieDto;
import com.poc.actor.entity.ActorEntity;
import com.poc.actor.entity.MovieEntity;

public class ActorMapper {
	
	public static ActorEntity copyPropertiesFromDto(ActorDto actorDto) {
		
		ActorEntity actorEntity = new ActorEntity();
		MovieEntity movieEntity;
		List<MovieEntity> movies = new ArrayList<>();
		
		actorEntity.setId(actorDto.getId());
		actorEntity.setName(actorDto.getName());
		actorEntity.setEmailId(actorDto.getEmailId());
		actorEntity.setDob(actorDto.getDob());
		actorEntity.setMobile(actorDto.getMobile());
		actorEntity.setFee(actorDto.getFee());
		actorEntity.setAvailable(actorDto.isAvailable());
		
		if(actorDto.getMovieDto() != null && !actorDto.getMovieDto().isEmpty() 
				&& actorDto.getMovieDto().size() >0) {
			
			for(MovieDto movie: actorDto.getMovieDto()) {
				movieEntity = MovieMapper.copyPropertiesFromDto(movie);
				movieEntity.setActorEntity(actorEntity);
				movies.add(movieEntity);
			}
		}
		
		actorEntity.setMovieEntities(movies);
		
		return actorEntity;		
	}
	
	public static ActorDto copyPropertiesFromEntity(ActorEntity actorEntity) {
		ActorDto actorDto = new ActorDto();
		MovieDto movieDto = new MovieDto();
		List<MovieDto> movies = new ArrayList<>();
		
		actorDto.setId(actorEntity.getId());
		actorDto.setName(actorEntity.getName());
		actorDto.setEmailId(actorEntity.getEmailId());
		actorDto.setDob(actorEntity.getDob());
		actorDto.setMobile(actorEntity.getMobile());
		actorDto.setFee(actorEntity.getFee());
		actorDto.setAvailable(actorEntity.isAvailable());
		
		if(actorEntity.getMovieEntities() != null 
				&& !actorEntity.getMovieEntities().isEmpty() 
				&& actorEntity.getMovieEntities().size() >0) {
			
			for(MovieEntity movie: actorEntity.getMovieEntities()) {
				movieDto = MovieMapper.copyPropertiesFromEntity(movie);
				movies.add(movieDto);
			}
		}
		
		actorDto.setMovieDto(movies);
		
		return actorDto;		
	}

}
