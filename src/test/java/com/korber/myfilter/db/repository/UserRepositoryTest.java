package com.korber.myfilter.db.repository;

import com.korber.myfilter.db.model.User;
import com.korber.myfilter.db.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void shouldReturnSomeUser(){
        final var user = repository.save(new User("Adam Levigne"));

        Optional<User> userOptional = repository.findById(user.getId());

        Assertions.assertEquals(user, userOptional.orElse(new User()));
        Assertions.assertEquals("Adam Levigne", userOptional.get().getName());
    }

}
