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
public class IssuesRepositoryTests {
	
	@Autowired
	private IssuesRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testSaveIssue() {
		
		Issues brokenTransformer = new Issues();
		brokenTransformer.setEquipment("Pole");
		brokenTransformer.setDescription("Missing Connector Cable");
		brokenTransformer.setLocation("Riverside");
		brokenTransformer.setStatus("Unattended");
		
		Issues savedIssue = repo.save(brokenTransformer);
		
		Issues existingIssue = entityManager.find(Issues.class, savedIssue.getId());
		
		assertThat(existingIssue.getId()).isEqualTo(savedIssue.getId());
	}
}
