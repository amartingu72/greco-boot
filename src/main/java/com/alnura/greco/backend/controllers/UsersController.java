package com.alnura.greco.backend.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alnura.greco.backend.model.CommunityDTO;
import com.alnura.greco.backend.model.UserDTO;
import com.alnura.greco.backend.service.UsersService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController
@RequestMapping(path="/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {
	private static final Logger logger = LoggerFactory.getLogger(UsersController.class);
	@Autowired
	UsersService usersService;
	
	@GetMapping("/{userId}")
	@ApiOperation(value = "Finds user by id",
    notes = "0,1,2...",
    response = UserDTO.class
    )
	@ApiResponses(value = { 
		      @ApiResponse(code = 400, message = "Invalid ID supplied"), 
		      @ApiResponse(code = 404, message = "User not found") })
	public UserDTO getUserById(
			@RequestHeader(value="Authentication", defaultValue="none") String authentication,
			@ApiParam(value = "user id", required = true, defaultValue="1") @PathVariable int userId)  {
		logger.info("Authentication: " + authentication);
        return usersService.findById(userId);
		
    }
	
	
	@PostMapping
	@ApiOperation(value = "Create user",
    response = UserDTO.class
    )
	@ApiResponses(value = { 
		@ApiResponse(code = 404, message = "User not found") })
	public UserDTO createUser(
			 @ApiParam(value = "User object", required = true) @RequestBody UserDTO userDTO)  {
        return usersService.create(userDTO);
		
    }
	
	@PutMapping("/{userId}")
	@ApiOperation(value = "Update user"  )
	@ApiResponses(value = { 
		@ApiResponse(code = 404, message = "User not found") })
	public void updateUser(
			@ApiParam(value = "user id", required = true, defaultValue="1") @PathVariable int userId,
			@ApiParam(value = "User object", required = true) @RequestBody UserDTO userDTO)  {
		
        usersService.update(userId, userDTO);
		
    }
	
	@DeleteMapping("/{userId}")
	@ApiOperation(value = "Finds user by id",
    notes = "0,1,2..."
    )
	@ApiResponses(value = { 
		      @ApiResponse(code = 400, message = "Invalid ID supplied"), 
		      @ApiResponse(code = 404, message = "User not found") })
	public void getUserById(
			@ApiParam(value = "user id", required = true, defaultValue="1") @PathVariable int userId)  {
		
        usersService.delete(userId);
		
    }
	
	@GetMapping("/{userId}/communities")
	@ApiOperation(value = "Finds communities where this user is member of",
    notes = "0,1,2...",
    response = CommunityDTO.class
    )
	@ApiResponses(value = { 
		      @ApiResponse(code = 400, message = "Invalid ID supplied"), 
		      @ApiResponse(code = 404, message = "User not found") })
	public List<CommunityDTO> getCommunities(
			
			@ApiParam(value = "user id", required = true, defaultValue="1") @PathVariable int userId)  {
		
        return usersService.getCommunities(userId);
		
    }
	
}
