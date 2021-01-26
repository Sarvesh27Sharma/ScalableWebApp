package com.github.sarvesh27sharma.ScalableWebApp;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ScalableWebAppApplicationTests {

	@Order(1)
	@Test
	void contextLoads() {
	}

	@Autowired
	protected MockMvc mockMvc;

	@Order(2)
	@Test
	void leftTest() throws Exception {
		mockMvc.perform(
				post("/v1/diff/1/left")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"data\":\"hello\"}"))
				.andExpect(content().string("data to be diff-ed submitted successfully"))
				.andExpect(status().is2xxSuccessful());
	}

	@Order(3)
	@Test
	void rightTest() throws Exception {
		mockMvc.perform(
				post("/v1/diff/1/right")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"data\":\"hello\"}"))
				.andExpect(content().string("data to be diff-ed submitted successfully"))
				.andExpect(status().is2xxSuccessful());
	}

	@Order(4)
	@Test
	void getIdTest() throws Exception {
		String expected="{" +
				"    \"status\": \"EQUAL\"," +
				"    \"differences\": null" +
				"}";
		mockMvc.perform(
				get("/v1/diff/1")
						)
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
		.andExpect(content().json(expected, false));
	}

}
