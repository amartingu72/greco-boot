package com.alnura.greco.backend.repository;

import org.springframework.data.repository.CrudRepository;
import com.alnura.greco.backend.entities.User;


public interface UsersDAO extends CrudRepository<User, Integer>{

}
