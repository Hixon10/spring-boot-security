package ru.hixon.microservice;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.hixon.microservice.entity.Role;
import ru.hixon.microservice.entity.User;
import ru.hixon.microservice.repository.UserRepository;

import java.util.List;
import java.util.UUID;

public class UserRepositoryTest extends AbstractComponentTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void canSaveUserTest() {
        String userName1 = "userName1";
        String id1 = UUID.randomUUID().toString();
        String password1 = "password1";

        User user = new User();
        user.setRoles(List.of(Role.ROLE_ADMIN));
        user.setUsername(userName1);
        user.setId(id1);
        user.setPassword(password1);

        // store user
        userRepository.save(user);

        // find stored user
        User foundUser = userRepository.findByUsername(userName1);

        Assert.assertEquals(id1, foundUser.getId());
        Assert.assertEquals(password1, foundUser.getPassword());
        Assert.assertEquals(userName1, foundUser.getUsername());
        Assert.assertEquals(Role.ROLE_ADMIN, foundUser.getRoles().get(0));

        // check, that user exists
        Assert.assertTrue(userRepository.existsByUsername(userName1));

        // Delete user
        userRepository.deleteByUsername(userName1);

        // Check, that user is not exect anymore
        Assert.assertFalse(userRepository.existsByUsername(userName1));
    }
}
