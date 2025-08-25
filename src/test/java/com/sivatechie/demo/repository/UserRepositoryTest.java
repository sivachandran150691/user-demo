package com.sivatechie.demo.repository;

import com.sivatechie.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveAndFindAll() {
        User u = new User();
        u.setName("Siva");
        u.setEmail("siva@example.com");

        User saved = userRepository.save(u);

        assertNotNull(saved.getId());
        List<User> all = userRepository.findAll();
        assertEquals(1, all.size());
        assertEquals("Siva", all.get(0).getName());
    }
}
