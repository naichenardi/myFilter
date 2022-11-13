package com.korber.myfilter.db.repository;

import com.korber.myfilter.db.model.User;
import com.korber.myfilter.db.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;

@DataJpaTest
class UserRepositoryTest {
    private final UserRepository repository;

    @Autowired
    public UserRepositoryTest(UserRepository repository) {
        this.repository = repository;
    }

    @Test
    public void shouldReturnSomeUser(){
        final var user = repository.save(new User("Adam Levigne"));

        Optional<User> userOptional = repository.findById(user.getId());

        Assertions.assertEquals(user, userOptional.orElse(new User()));
        Assertions.assertEquals("Adam Levigne", userOptional.get().getName());
    }

}
