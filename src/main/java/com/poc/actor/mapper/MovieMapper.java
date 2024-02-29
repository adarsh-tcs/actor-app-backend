package com.poc.actor.mapper;

import java.util.ArrayList;
import java.util.List;

import com.poc.actor.dto.ActorDto;
import com.poc.actor.dto.MovieDto;
import com.poc.actor.entity.ActorEntity;
import com.poc.actor.entity.MovieEntity;
import com.poc.actor.util.MovieStatus;

public class MovieMapper {
	
public static MovieEntity copyPropertiesFromDto(MovieDto moviesDto) {
		
		MovieEntity movieEntity;
		
		movieEntity = new MovieEntity();
		movieEntity.setId(moviesDto.getId());
		movieEntity.setName(moviesDto.getName());
		movieEntity.setDirectorName(moviesDto.getDirectorName());
		movieEntity.setReleaseYear(moviesDto.getReleaseYear());
		movieEntity.setStatus(moviesDto.getStatus());
		
		return movieEntity;		
	}
	
	public static MovieDto copyPropertiesFromEntity(MovieEntity moviesEntity) {

		MovieDto movieDto;
		
		movieDto = new MovieDto();
		movieDto.setId(moviesEntity.getId());
		movieDto.setName(moviesEntity.getName());
		movieDto.setDirectorName(moviesEntity.getDirectorName());
		movieDto.setReleaseYear(moviesEntity.getReleaseYear());
		movieDto.setStatus(moviesEntity.getStatus());
			
		return movieDto;		
	}


}
