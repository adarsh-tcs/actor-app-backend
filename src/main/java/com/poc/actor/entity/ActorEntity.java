package com.poc.actor.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="tbl_actor_details")
public class ActorEntity implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "ACTOR_NAME")
	private String name;
	
	@Column(name = "ACTOR_MOBILE")
	private String mobile;
	
	@Column(name="ACTOR_EMAIL")
	private String emailId;
	
	@Column(name="ACTOR_DOB")
	private LocalDate dob;
	
	@Column(name = "ACTOR_FEE")
	private double fee;
	
	@Column(name = "AVAILABLE")
	private boolean isAvailable;
	
	@OneToMany(mappedBy="actorEntity", cascade=CascadeType.ALL)
	private List<MovieEntity> movieEntities;

}
