package com.manish.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manish.productservice.dto.ProductRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void shouldCreateProduct() throws Exception {
		String productRequestString = objectMapper.writeValueAsString(getProductRequest());

		mockMvc
				.perform(MockMvcRequestBuilders
					.post("/api/product")
					.contentType(MediaType.APPLICATION_JSON)
					.content(productRequestString))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void shouldGetAllProducts() throws Exception {
		mockMvc
				.perform(MockMvcRequestBuilders
						.get("/api/product"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("IPhone 11")
				.description("Brand new Smart Phone")
				.price(BigDecimal.valueOf(2000))
				.build();
	}

}
