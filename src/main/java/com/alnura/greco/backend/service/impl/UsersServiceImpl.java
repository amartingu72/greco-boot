package com.alnura.greco.backend.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alnura.greco.backend.entities.Community;
import com.alnura.greco.backend.entities.User;
import com.alnura.greco.backend.exceptions.UserAlreadyExistException;
import com.alnura.greco.backend.exceptions.UserNotFoundException;
import com.alnura.greco.backend.model.CommunityDTO;
import com.alnura.greco.backend.model.UserDTO;
import com.alnura.greco.backend.repository.CommunitiesDAO;
import com.alnura.greco.backend.repository.UsersDAO;
import com.alnura.greco.backend.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{
	@Autowired
	UsersDAO usersDao;
	
	@Autowired
	CommunitiesDAO communitiesDAO;
	
	private User DTOToUser(UserDTO userDTO) {
		User user=new User();
		user.setId(userDTO.getId());
		user.setMydata(userDTO.getMydata());
		user.setEmail(userDTO.getEmail());
		user.setAdds( (byte)(userDTO.isAdds()? 1 : 0) );
		user.setNickname(userDTO.getNickname());
		return user;
	}
	
	private UserDTO UserToDTO(User user) {
		return UserDTO.builder()
		.id(user.getId())
		.mydata(user.getMydata())
		.email(user.getEmail())
		.adds(user.getAdds()!=0)
		.nickname(user.getNickname())
		.build();
	}
	
	@Override
	public UserDTO findById(int id) throws UserNotFoundException {
		UserDTO userDTO=null;
		User user;
		Optional<User> userEntity=usersDao.findById(id);
		if (userEntity.isPresent() ) {
			user=userEntity.get();
			userDTO=this.UserToDTO(user);
		}
		else
			throw new UserNotFoundException();
		return userDTO;
	}

	@Override
	public UserDTO create(UserDTO userDTO) throws UserAlreadyExistException {
		//check if user exists.
		if ( this.usersDao.existsById(userDTO.getId()) )
			throw new UserAlreadyExistException();
		//create user
		User user=this.usersDao.save(this.DTOToUser(userDTO));
		return this.UserToDTO(user);
	}

	@Override
	public void update(int userId, UserDTO userDTO) throws UserNotFoundException {
		//check if user exists.
		if ( !this.usersDao.existsById(userId) )
			throw new UserNotFoundException();
		userDTO.setId(userId);
		this.usersDao.save(this.DTOToUser(userDTO));
		
	}

	@Override
	public void delete(int id) throws UserNotFoundException {
		//check if user exists.
		if ( !this.usersDao.existsById(id) )
		 throw new UserNotFoundException();
		this.usersDao.deleteById(id);
	}

	@Override
	@Transactional
	public List<CommunityDTO> getCommunities(int userId) {
		Optional<User> userOpt=this.usersDao.findById(userId);
		List<CommunityDTO> dtoCommunities=null;
		List<Community> communities=userOpt.get().getCommunities();
		if ( communities != null ) { 
			dtoCommunities=(communities)
						.stream()
						.map(c -> CommunityDTO.builder()
										.id(c.getId())
										.name(c.getName())
										.notes(c.getNotes())
										.available(c.getAvailable()!=0 )
										.zipcode(c.getZipcode())
										.country(c.getCountry().getName())
										.build())						
						.collect(Collectors.toList());
			
		}
				
		return dtoCommunities;
	}
	


}
