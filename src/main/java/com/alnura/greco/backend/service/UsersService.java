package com.alnura.greco.backend.service;

import com.alnura.greco.backend.exceptions.UserAlreadyExistException;
import com.alnura.greco.backend.exceptions.UserNotFoundException;
import com.alnura.greco.backend.model.UserDTO;

public interface UsersService {
	
	public UserDTO findById(int id) throws UserNotFoundException;
	
	public UserDTO create(UserDTO userDTO) throws UserAlreadyExistException;
}
