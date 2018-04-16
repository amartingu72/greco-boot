package com.alnura.greco.backend.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alnura.greco.backend.entities.User;
import com.alnura.greco.backend.exceptions.UserAlreadyExistException;
import com.alnura.greco.backend.exceptions.UserNotFoundException;
import com.alnura.greco.backend.model.UserDTO;
import com.alnura.greco.backend.repository.UsersDAO;
import com.alnura.greco.backend.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{
	@Autowired
	UsersDAO usersDao;
	
	@Override
	public UserDTO findById(int id) throws UserNotFoundException {
		UserDTO userDTO=null;
		User user;
		Optional<User> userEntity=usersDao.findById(id);
		if (userEntity.isPresent() ) {
			user=userEntity.get();
			userDTO=UserDTO.builder()
				.id(user.getId())
				.mydata(user.getMydata())
				.email(user.getEmail())
				.adds(user.getAdds()!=0)
				.nickname(user.getNickname())
				.build();
		}
		else
			throw new UserNotFoundException();
		return userDTO;
	}

	@Override
	public UserDTO create(UserDTO userDTO) throws UserAlreadyExistException {
		
		return userDTO;
	}
	


}
