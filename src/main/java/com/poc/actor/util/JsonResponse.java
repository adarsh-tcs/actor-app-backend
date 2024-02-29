package com.poc.actor.util;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class JsonResponse {
	
	private String status = null;
	
	private int statusCode;
	
	private String message = null;
	
	private Object payload = null;

}
