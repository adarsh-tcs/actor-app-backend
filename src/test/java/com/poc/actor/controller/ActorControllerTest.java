package com.poc.actor.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.poc.actor.dto.ActorDto;
import com.poc.actor.service.ActorService;
import com.poc.actor.util.JsonResponse;

@WebMvcTest(ActorController.class)
public class ActorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ActorService actorService;
	
	@Mock
	ActorDto actor;
	
	@Test
	public void addActor_Fail() throws Exception {
		
		JsonResponse jsonResponse = new JsonResponse("Ok", 200, "Data Saved" ,null );
		ResponseEntity<JsonResponse> responseEntity = new ResponseEntity<JsonResponse> (jsonResponse, HttpStatus.EXPECTATION_FAILED);
		when(actorService.saveActor(any(ActorDto.class))).thenReturn(responseEntity);
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/v1/api/public/addActor")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("{}");
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isExpectationFailed())
				.andExpect(content().json(
						"{status: Ok, statusCode:200, message:\"Data Saved\", payload:null}"))
				.andExpect(jsonPath("$.status").value("Ok"))
				.andReturn();
			
	}
	
	@Test
	public void addActor_Success() throws Exception {
		
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
		
		JsonResponse jsonResponse = new JsonResponse("Created", 201, "Data Saved" ,null );
		ResponseEntity<JsonResponse> responseEntity = new ResponseEntity<JsonResponse> (jsonResponse, HttpStatus.CREATED);
		
		when(actorService.saveActor(any(ActorDto.class))).thenReturn(responseEntity);
		
		System.out.println(actorService.saveActor(actor));
		RequestBuilder request = MockMvcRequestBuilders
				.post("/v1/api/public/addActor")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(input);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andExpect(content().json(
						"{status: Created, statusCode:201, message:\"Data Saved\", payload:null}"))
				.andExpect(jsonPath("$.status",is("Created")))
				.andReturn();
	}

	@Test
	public void actorList_EmptyPayload() throws Exception {
		when(actorService.getActorList()).thenReturn(new ResponseEntity<List<String>> (List.of(), HttpStatus.OK));
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/v1/api/public/getActorList")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("[ ]"))
				.andReturn();
		
//		assertEquals("", result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void actorList_EmptyListPayload() throws Exception {
		when(actorService.getActorList())
			.thenReturn(new ResponseEntity<List<String>> (
							new ArrayList<String>(Arrays.asList("Salman Khan", "Amitabh")), 
					HttpStatus.OK));
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/v1/api/public/getActorList")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("[\"Salman Khan\", Amitabh]"))
				.andReturn();
		
	}
	
//	@Test
//	public void actorList_DataInPayload() throws Exception {
//		when(actorService.getActorList())
//			.thenReturn(new ResponseEntity<List<String>> 
//			( 
//					new ArrayList<String>(Arrays.asList()) ), HttpStatus.OK));
//		
//		RequestBuilder request = MockMvcRequestBuilders
//				.get("/v1/api/public/getActorList")
//				.accept(MediaType.APPLICATION_JSON);
//		
//		MvcResult result = mockMvc.perform(request)
//				.andExpect(status().isOk())
//				.andExpect(content().json("{status: Ok, statusCode:200, message:message, payload:[]}"))
//				.andReturn();
//		
//	}
}
