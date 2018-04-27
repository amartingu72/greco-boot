package com.alnura.greco.backend.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.alnura.greco.backend.app.Application;
import com.alnura.greco.backend.entities.User;
import com.alnura.greco.backend.model.UserDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = WebEnvironment.RANDOM_PORT,
  classes = Application.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestEntityManager
public class UsersControllerTest {
	@Autowired
    private MockMvc mvc;
	
	@Autowired
    private TestEntityManager entityManager;  //Util para los tests
	
		
	@Test
	@Transactional
	public void find_a_userId_that_exists() throws Exception {
		// given
		User user=new User();
		user.setId(3);
		user.setEmail("alberto.martin@alnura.es");
		user.setMydata("Mis dato");
		user.setAdds((byte)1 );
		user.setNickname("alberto");
		entityManager.persist(user);
		entityManager.flush();	
		
		mvc.perform(get("/v1/users/{id}",3)
		      .contentType(MediaType.APPLICATION_JSON))
		      .andExpect(status().isOk())
		      .andExpect(content()
		    	  .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
		      .andExpect(jsonPath("$.email", is("alberto.martin@alnura.es")));
		
		entityManager.remove(user);
	}
	
	@Test
	public void find_a_userId_that_doesnt_exists() throws Exception {
	 mvc.perform(get("/v1/users/{id}",1)
		      .contentType(MediaType.APPLICATION_JSON))
		      .andExpect(status().isBadRequest())
		      .andExpect(content()
		    	  .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
		      .andExpect(jsonPath("$.code", is("0001")));
	}
	
	@Test
	@Transactional
	public void create_a_user() throws Exception {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String userJSON=gson.toJson(UserDTO.builder()
				.id(1)
				.mydata("my data")
				.email("mytest@alnura.es")
				.adds(true)
				.nickname("my")
				.build()); 
		mvc.perform(post("/v1/users")
				.content(userJSON)
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			    	  .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
			      .andExpect(jsonPath("$.id", greaterThan(0)))
			      .andExpect(jsonPath("$.email", is("mytest@alnura.es")));
		
		entityManager.remove(entityManager.find(User.class, 1));
		
	}
	
	@Test
	@Transactional
	public void create_a_user_that_already_exist() throws Exception {
		// given
		User user=new User();
		user.setId(3);
		user.setEmail("alberto.martin@alnura.es");
		user.setMydata("Mis datos");
		user.setAdds((byte)1 );
		user.setNickname("alberto");
		entityManager.persist(user);
		entityManager.flush();	
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String userJSON=gson.toJson(UserDTO.builder()
				.id(3)
				.mydata("my data")
				.email("mytest@alnura.es")
				.adds(true)
				.nickname("my")
				.build()); 
		
		mvc.perform(post("/v1/users")
				.content(userJSON)
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest())
			      .andExpect(content()
			    	  .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
			      .andExpect(jsonPath("$.code", is("0002")));
		
		entityManager.remove(entityManager.find(User.class, 3));
	}
	
	@Test
	@Transactional
	public void delete_user() throws Exception {
		// given
		User user=new User();
		user.setId(3);
		user.setEmail("alberto.martin@alnura.es");
		user.setMydata("Mis datos");
		user.setAdds((byte)1 );
		user.setNickname("alberto");
		entityManager.persist(user);
		entityManager.flush();
		
		//delete user
		mvc.perform(delete("/v1/users/{id}",3)
				.contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isOk() );
		
		user=entityManager.find(User.class, 3);
		
		assertNull(user);
			
	}
	
	@Test
	public void delete_user_that_doesnt_exist() throws Exception {
		
		
		//delete user
		mvc.perform(delete("/v1/users/{id}",3)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.code", is("0001")));
			    
		
	}
	
	@Test
	@Transactional
	public void update_a_user() throws Exception {
		//Create user
		User user=new User();
		user.setId(1);
		user.setEmail("alberto.martin@alnura.es");
		user.setMydata("Mis datos");
		user.setAdds((byte)1 );
		user.setNickname("alberto");
		entityManager.persist(user);
		entityManager.flush();


		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String userJSON=gson.toJson(UserDTO.builder()
				.id(1)
				.mydata("my data")
				.email("mytest@alnura.es")
				.adds(true)
				.nickname("my")
				.build()); 
		mvc.perform(put("/v1/users/{id}",1)
				.content(userJSON)
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
		
		User updatedUser=entityManager.find(User.class, 1);
		assertThat(updatedUser.getEmail(), is("mytest@alnura.es"));
		assertThat(updatedUser.getMydata(), is("my data"));
		assertThat(updatedUser.getNickname(), is("my"));
				
		entityManager.remove(user);
		
	}
	
	@Test
	public void update_a_user_that_doesnt_exist() throws Exception {


		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String userJSON=gson.toJson(UserDTO.builder()
				.id(1)
				.mydata("my data")
				.email("mytest@alnura.es")
				.adds(true)
				.nickname("my")
				.build()); 
		mvc.perform(put("/v1/users/{id}",1)
				.content(userJSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.code", is("0001")));



	}
	
	
}
