package com.example.blog_management;

import com.example.blog_management.Repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BlogManagementApplicationTests {
	@Autowired
	UserRepo userRepo;

	@Test
	void contextLoads() {
		assertEquals(4,2+2);
		assertNotNull(userRepo.findByUsername("nam"));
	}

}
