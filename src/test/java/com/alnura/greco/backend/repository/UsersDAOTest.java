package com.alnura.greco.backend.repository;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;


import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alnura.greco.backend.app.Application;
import com.alnura.greco.backend.entities.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@AutoConfigureTestEntityManager
public class UsersDAOTest {
	@Autowired
    private TestEntityManager entityManager;  //Util para los tests
	
	@Autowired
	private UsersDAO usersDAO;
	
	@Test
	@Transactional
	public void whenFindByName_thenReturnUser() {
	    // given
		User user=new User();
		user.setId(3);
		user.setEmail("alberto.martin@alnura.es");
		user.setMydata("Mis dato");
		user.setAdds((byte)1 );
		user.setNickname("alberto");
	    entityManager.persist(user);
	    entityManager.flush();
	 
	    // when
	    Optional<User> userFound = usersDAO.findById(user.getId());
	 
	    // then
	    assertTrue(userFound.isPresent());
	    assertThat( userFound.get().getEmail(), is(user.getEmail()));
	}
}
