package com.geekbrains.ru.gb_rest_angular.core.test;

import com.geekbrains.ru.gb_rest_angular.core.domain.Role;
import com.geekbrains.ru.gb_rest_angular.core.domain.User;
import com.geekbrains.ru.gb_rest_angular.core.repository.UserRepository;
import com.geekbrains.ru.gb_rest_angular.core.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    public void addNewUserTest(){

        Role role = new Role("ROLE_ADMIN");
        Role role1 = new Role("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        List<Role> roles1 = new ArrayList<>();
        roles.add(role);
        roles1.add(role1);

        User user = new User();
        user.setId(678L);
        user.setUserName("Boris");
        user.setSurname("Johnson");
        user.setPhone("+8881234567");
        user.setPassword("456");
        user.setEmail("JohnsonBS83@mail.ru");
        user.setRoles(roles);

        User user2 = new User();
        user2.setUserName("Ben");
        user2.setSurname("Johnson");
        user2.setPhone("+8887654321");
        user2.setEmail("JohnsonBS83@mail.ru");
        user2.setPassword("123");
        user2.setRoles(roles1);
        Mockito.doReturn(Optional.of(user)).when(userRepository).findUserByEmail("JohnsonBS83@mail.ru");

        Mockito.doReturn(user).when(userRepository).save(user);

        User user1 = userService.addNewUser(user2);

        Assertions.assertNotNull(user1);
        Assertions.assertEquals(user2.getUserName(),user1.getUserName());
        Assertions.assertEquals(user2.getPhone(), user1.getPhone());
        Assertions.assertEquals(user.getId(), user1.getId());
        Mockito.verify(userRepository, times(1)).save(user);
    }

    @Test
    public void loadUserByUsernameTest() {
        Role role = new Role("ROLE_ADMIN");
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        User user = new User();
        user.setId(678L);
        user.setUserName("Boris");
        user.setSurname("Johnson");
        user.setPhone("+8881234567");
        user.setPassword("456");
        user.setEmail("JohnsonBS83@mail.ru");
        user.setRoles(roles);

        Mockito.doReturn(Optional.of(user)).when(userRepository).findUserByEmail("JohnsonBS83@mail.ru");

        UserDetails userDetails = userService.loadUserByUsername("JohnsonBS83@mail.ru");

        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(user.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(user.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(1, userDetails.getAuthorities().size());
        Assertions.assertTrue(userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN")));
    }
}
