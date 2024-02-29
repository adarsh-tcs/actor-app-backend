package com.poc.actor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poc.actor.util.MovieStatus;

import lombok.Data;

@Data
public class MovieDto {

	private Integer id;
	
	private String name;
	
	private String directorName;
	
	private int releaseYear;
	
	private MovieStatus status;
	
	@JsonIgnore
	private ActorDto actorDto;
}
