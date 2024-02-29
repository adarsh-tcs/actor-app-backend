package com.poc.actor.controller;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import com.poc.actor.dto.ActorDto;
import com.poc.actor.dto.MovieDto;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class ActorControllerIT {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void getActor_IT() throws JSONException {
		String response = this.restTemplate.getForObject("/v1/api/public/getAllRecord", String.class);
		JSONAssert.assertEquals("{statusCode:0}]", response, false);
		JSONAssert.assertEquals("{statusCode:0, payload:{id:1010}}", response, false);
		JSONAssert.assertEquals("{statusCode:0, payload:{id:1010, movieDto:[{}, {}]}}", response, false);
		JSONAssert.assertEquals("{statusCode:0, payload:{id:1010, movieDto:[{id:1001}, {id:1002}]}}", response, false);
		JSONAssert.assertEquals("{statusCode:0, payload:{id:1010, name:abc, movieDto: [{"
				+ "                \"id\": 1001,"
				+ "                \"name\": \"Tiger\","
				+ "                \"directorName\": \"fdhgj\","
				+ "                \"releaseYear\": 2345,"
				+ "                \"status\": \"RELEASED_ON_OTT\""
				+ "            },"
				+ "            {"
				+ "                \"id\": 1002,"
				+ "                \"name\": \"Hero\","
				+ "                \"directorName\": \"hgxg\","
				+ "                \"releaseYear\": 1999,"
				+ "                \"status\": \"AVAILABLE_IN_DVD_ONLY\""
				+ "            }"
				+ "        ]}}", response, false);
	}
	
	@Test
	public void addActor_NoInput() throws JSONException {
		ActorDto actor = new ActorDto();
		String response = this.restTemplate.postForObject("/v1/api/public/addActor", actor, String.class);
		String expected = "{status: null, statusCode:201, message:\"Data Saved in DB\", payload:null}";
		JSONAssert.assertEquals(expected, response, false);
	}
	
	@Test
	public void addActor_sucess() throws JSONException {
		String input = "{ \"name\": \"ab bc\","
				+ "    \"mobile\": \"9876543210\","
				+ "    \"emailId\": \"abc@gmail.com\","
				+ "    \"dob\": \"1999-07-02\","
				+ "    \"fee\": 300.0,"
				+ "    \"movieDto\": [{"
				+ "        \"name\": \"ytdj\","
				+ "        \"directorName\": \"jhvjh\","
				+ "        \"releaseYear\": 1982,"
				+ "        \"status\": 1"
				+ "    },"
				+ "    {"
				+ "        \"name\": \"ncncjh\","
				+ "        \"directorName\": \"hgd\","
				+ "        \"releaseYear\": 1989,"
				+ "        \"status\":3"
				+ "    }],"
				+ "    \"available\": false"
				+ "}";
		
		MovieDto movieDto1 = new MovieDto();
		movieDto1.setName("Animal");
		MovieDto movieDto2 = new MovieDto();
		movieDto2.setName("Tiger 3");
		ActorDto actorDto = new ActorDto();
		actorDto.setId(1000);
		actorDto.setName("Salman");
		
		
		String response = this.restTemplate.postForObject("/v1/api/public/addActor", actorDto, String.class);
		String expected = "{status: null, statusCode:201, message:\"Data Saved in DB\", payload:null}";
		JSONAssert.assertEquals(expected, response, false);
	}

}
