//package ru.madeira.onlinelibrarymanagementsystem.service;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.junit4.SpringRunner;
//import static org.mockito.Mockito.when;
//import ru.madeira.onlinelibrarymanagementsystem.dto.UserDTO;
//import ru.madeira.onlinelibrarymanagementsystem.entity.User;
//import ru.madeira.onlinelibrarymanagementsystem.mapper.UserMapper;
//import ru.madeira.onlinelibrarymanagementsystem.repository.UserRepository;
//import ru.madeira.onlinelibrarymanagementsystem.util.PasswordSecurityGenerator;
//
//import java.time.LocalDate;
//import java.util.HashSet;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//class UserServiceTest {
//
//    @InjectMocks
//    private UserService userService;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @MockBean
//    private UserMapper userMapper;
//
//    @MockBean
//    private RoleService roleService;
//
//    @MockBean
//    private PasswordEncoder passwordEncoder;
//
//    @MockBean
//    private PasswordSecurityGenerator passwordSecurityGenerator;
//
//    @MockBean
//    private MailSenderService mailSenderService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        userService = new UserService(userRepository, userMapper, roleService, passwordEncoder, passwordSecurityGenerator, mailSenderService);
//    }
//
//    @Test
//    void getAllUsers() {
//    }
//
//    @Test
//    void getAllReaders() {
//    }
//
//    @Test
//    void findUserByReadersTicketNumber() {
//    }
//
//    @Test
//    void createNewUser() {
//        UserDTO userDTO = new UserDTO("Test", "Test", "Test", LocalDate.now(), new HashSet<>(), "Test", "Test", 1111L);
//        userService.createNewUser(userDTO);
//        assertTrue(userRepository.existsByLoginOrEmail("Test", "test@gmail.com"));
//        when()
//    }
//
//    @Test
//    void getUserById() {
//
//    }
//
//    @Test
//    void getAllUsersWhichIdInList() {
//    }
//
//    @Test
//    void getUserByLogin() {
//    }
//
//    @Test
//    void editMyAccountData() {
//    }
//
//    @Test
//    void addRoleToUser() {
//    }
//}