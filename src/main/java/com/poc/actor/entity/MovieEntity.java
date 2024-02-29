package com.poc.actor.entity;

import java.io.Serializable;

import com.poc.actor.util.MovieStatus;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="tbl_movie_details")
public class MovieEntity implements Serializable {
	
	private static final long serialVersionUID = -1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="MOVIE_NAME")
	private String name;
	
	private String directorName;
	
	private int releaseYear;
	
	private MovieStatus status;
	
	@ManyToOne
	@JoinColumn(name = "ACTOR_ID")
	private ActorEntity actorEntity;

}
