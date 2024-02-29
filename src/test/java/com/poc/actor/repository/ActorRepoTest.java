package com.poc.actor.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ActorRepoTest {
	
	@Autowired
	private ActorRepository actorRepo;

	@Test
	void fetchActorNameTest() {
		
		List<String> names = actorRepo.findName();
		assertEquals(2, names.size());
		
		//hamcrest
		assertThat(names, hasSize(2));
		assertThat(names, hasItems("abc", "xyz"));
		
		
		//AssterJ
		assertThat(names).hasSize(2).contains("abc", "xyz");
	}

}
