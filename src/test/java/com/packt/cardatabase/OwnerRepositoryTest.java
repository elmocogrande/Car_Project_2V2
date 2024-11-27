package com.packt.cardatabase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;

@DataJpaTest
public class OwnerRepositoryTest {
	
	@Autowired
	private OwnerRepository ownerRepo;
	
	@Test
	void saveOwner() {
		ownerRepo.save(new Owner("Lucy", "Smith"));
		assertThat(ownerRepo.findByFirstname("Lucy").isPresent()).isTrue();
	}
	
	@Test
	void deleteOwners() {
		ownerRepo.save(new Owner("Lucy", "Morrison"));
		ownerRepo.deleteAll();
		
		assertThat(ownerRepo.count()).isEqualTo(0);
	}

}
