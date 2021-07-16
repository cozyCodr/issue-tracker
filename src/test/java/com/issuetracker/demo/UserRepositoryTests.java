package com.issuetracker.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testSaveUser() {
		
		User ngoloKante = new User();
		ngoloKante.setFirstName("Bright");
		ngoloKante.setLastName("Londa");
		ngoloKante.setEmail("bright@gmail.com");
		ngoloKante.setPassword("bright2021");
		
		User savedUser = userRepo.save(ngoloKante);
		
		Issues existingAdmin = entityManager.find(Issues.class, savedUser.getId());
		
		assertThat(existingAdmin.getId()).isEqualTo(savedUser.getId());
	}
	
	@Test
	public void testFindUserByEmail() {
		String email = "olivertwist@gmail.com";
		User user = userRepo.findByEmail(email);
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testAddRoleToNewUser() {
		User brianMwila = new User();
		brianMwila.setFirstName("Brian");
		brianMwila.setLastName("Mwila");
		brianMwila.setEmail("brian@gmail.com");
		brianMwila.setPassword("brian2021");
		
		Role roleUser = roleRepo.findByName("User");
		brianMwila.addRole(roleUser);
		
		User savedUser = userRepo.save(brianMwila);
		
		assertThat(savedUser.getRoles().size()).isEqualTo(1);
	}
	
	@Test
	public void testAddRolesToExistinfUse() {
		User user = userRepo.findById(1L).get();
		
		Role roleUser = roleRepo.findByName("User");
		user.addRole(roleUser);
		
		Role roleAdmin = new Role(2L);
		user.addRole(roleAdmin);
		
		User savedUser = userRepo.save(user);
		
		assertThat(savedUser.getRoles().size()).isEqualTo(2);
	}
	
}
