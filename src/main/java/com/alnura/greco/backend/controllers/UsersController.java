package com.alnura.greco.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alnura.greco.backend.model.UserDTO;
import com.alnura.greco.backend.service.UsersService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Mono;



@RestController
@RequestMapping("/v1/users")
public class UsersController {
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
	public Mono<UserDTO> getUserById(
			 @ApiParam(value = "user id", required = true) @PathVariable int userId)  {
        return Mono.just(usersService.findById(userId));
		
    }
	
	@PostMapping
	@ApiOperation(value = "Create user",
    response = UserDTO.class
    )
	@ApiResponses(value = { 
		@ApiResponse(code = 404, message = "User not found") })
	public Mono<UserDTO> updateUser(
			 @ApiParam(value = "User object", required = true) @RequestBody UserDTO userDTO)  {
        return Mono.just(usersService.create(userDTO));
		
    }
	
	
}
