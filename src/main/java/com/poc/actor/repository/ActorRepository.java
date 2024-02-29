package com.poc.actor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poc.actor.entity.ActorEntity;

@Repository
public interface ActorRepository extends JpaRepository<ActorEntity, Integer>{
	
	@Query(value="Select ACTOR_NAME from TBL_ACTOR_DETAILS;", nativeQuery=true)
	public List<String> findName();

	public ActorEntity findByMobile(String mobile);
	
	public ActorEntity findByName(String name);
}
