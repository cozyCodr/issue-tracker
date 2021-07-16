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
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class EquipmentRepositoryTests {

	@Autowired
	private EquipmentRepository repository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testSaveEquipment() {
		
		Equipment transformer = new Equipment();
		transformer.setName("Transformer");
		transformer.setDescription("Bought in America with Chinese donor money");
		transformer.setLocation("Avondale");
		
		Equipment savedEquipment = repository.save(transformer);
		
		Equipment existingEquipment = entityManager.find(Equipment.class, savedEquipment.getId());
		
		assertThat(existingEquipment.getId()).isEqualTo(savedEquipment.getId());
	}
}

