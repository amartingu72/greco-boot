package com.alnura.greco.backend.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.alnura.greco.backend.entities.User;
import com.alnura.greco.backend.model.UserDTO;
import com.alnura.greco.backend.repository.UsersDAO;
import com.alnura.greco.backend.service.impl.UsersServiceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	@Mock
	UsersDAO usersDAO;
	
	@InjectMocks
	UsersServiceImpl userService;
	
	@Test
	public void  findById(){
		User user=new User();
		user.setId(3);
		user.setEmail("alberto.martin@alnura.es");
		user.setMydata("Mis dato");
		user.setAdds((byte)1 );
		user.setNickname("alberto");
				
		when(usersDAO.findById(3)).thenReturn(Optional.of(user));
		
		UserDTO userDTO=userService.findById(3);
		assertEquals(userDTO.getId(), 3);
		assertEquals(userDTO.getEmail(),"alberto.martin@alnura.es");
			
	}
	
	@Test
	public void  deleteById(){
						
		when(usersDAO.existsById(3)).thenReturn(true);
		//doNothing().when(usersDAO).delete( any() );  //Void method doesn't need it in this version.
		
		userService.delete(3);
		
		
			
	}
}
