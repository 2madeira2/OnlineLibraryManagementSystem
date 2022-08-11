package ru.madeira.onlinelibrarymanagementsystem.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ru.madeira.onlinelibrarymanagementsystem.dto.UserDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.User;
import ru.madeira.onlinelibrarymanagementsystem.mapper.UserMapper;
import ru.madeira.onlinelibrarymanagementsystem.repository.UserRepository;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    private AutoCloseable autoCloseable;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserService()
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getAllReaders() {
    }

    @Test
    void findUserByReadersTicketNumber() {
    }

    @Test
    void createNewUser() {
        UserDTO userDTO = new UserDTO("Test", "Test", "Test", LocalDate.now(), new HashSet<>(), "Test", "Test", 1111L);
        userService.createNewUser(userDTO);
        assertTrue(userRepository.existsByLoginOrEmail("Test", "test@gmail.com"));
    }

    @Test
    void getUserById() {

    }

    @Test
    void getAllUsersWhichIdInList() {
    }

    @Test
    void getUserByLogin() {
    }

    @Test
    void editMyAccountData() {
    }

    @Test
    void addRoleToUser() {
    }
}