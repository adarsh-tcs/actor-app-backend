package com.poc.actor.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ActorDto {

	private Integer id;
	
	private String name;
	
	private String mobile;
	
	private String emailId;
	
	private LocalDate dob;
	
	private double fee;
	
	private boolean isAvailable;
	
	private List<MovieDto> movieDto;
}
