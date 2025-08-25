package com.sivatechie.demo;

import com.sivatechie.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createAndGetUsers() {
        User u = new User();
        u.setName("Siva");
        u.setEmail("siva@example.com");

        ResponseEntity<User> post = restTemplate.postForEntity("/users", u, User.class);
        assertThat(post.getStatusCode().is2xxSuccessful()).isTrue();
        User created = post.getBody();
        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();

        ResponseEntity<User[]> get = restTemplate.getForEntity("/users", User[].class);
        assertThat(get.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(get.getBody()).isNotEmpty();
    }
}
