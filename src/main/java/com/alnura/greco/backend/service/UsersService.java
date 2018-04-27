package com.alnura.greco.backend.service;

import java.util.List;

import com.alnura.greco.backend.exceptions.UserAlreadyExistException;
import com.alnura.greco.backend.exceptions.UserNotFoundException;
import com.alnura.greco.backend.model.CommunityDTO;
import com.alnura.greco.backend.model.UserDTO;

public interface UsersService {
	
	public UserDTO findById(int id) throws UserNotFoundException;
	
	public UserDTO create(UserDTO userDTO) throws UserAlreadyExistException;
	
	public void update(int userId, UserDTO userDTO) throws UserNotFoundException;
	
	public void delete(int id) throws UserNotFoundException;
	
	public List<CommunityDTO> getCommunities(int userId);
}
