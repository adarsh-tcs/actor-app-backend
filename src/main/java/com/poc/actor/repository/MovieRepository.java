package com.poc.actor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.actor.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

}
