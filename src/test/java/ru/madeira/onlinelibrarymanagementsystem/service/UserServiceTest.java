package ru.madeira.onlinelibrarymanagementsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.madeira.onlinelibrarymanagementsystem.dto.UserDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Role;
import ru.madeira.onlinelibrarymanagementsystem.entity.User;
import ru.madeira.onlinelibrarymanagementsystem.mapper.UserMapper;
import ru.madeira.onlinelibrarymanagementsystem.repository.UserRepository;
import ru.madeira.onlinelibrarymanagementsystem.util.PasswordSecurityGenerator;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("В данном тесте UserService ")
class UserServiceTest {

    private static final Long ID = 1L;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private RoleService roleService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private PasswordSecurityGenerator passwordSecurityGenerator;

    @Mock
    private MailSenderService mailSenderService;

    @InjectMocks
    private UserService userService;

//    @BeforeEach
//    public void init() {
//        this.userService = new UserService(userRepository, userMapper, roleService, passwordEncoder, passwordSecurityGenerator, mailSenderService);
//    }

    @Test
    void getUserByIdTest() {
//        final User expectedUser = mock(User.class);
//        when(userRepository.findUserById(ID)).thenReturn(Optional.of(expectedUser));
//        when(userMapper.toDto(Mockito.any(User.class))).thenReturn()
//
//        final UserDTO actualUser = userService.getUserById(ID);
//
//        assertNotNull(actualUser);
//        assertEquals(userMapper.toDto(expectedUser), actualUser);
//        verify(userRepository).findUserById(ID);
    }

    @Test
    void getAllUsersTest() {
        List<User> expectedUsersList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            expectedUsersList.add(mock(User.class));
        }
        when(userRepository.findAll()).thenReturn(expectedUsersList);

        List<UserDTO> actualUsersList = userService.getAllUsers();

        assertEquals(expectedUsersList.size(), actualUsersList.size());
        assertEquals(userMapper.toDto(expectedUsersList), actualUsersList);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName(" метод должен добавить роль юзеру и сохранить в БД ")
    void shouldSaveUserWithAddedNewRole() {
        User user = new User();
        user.setId(1L);
        user.setRoles(new HashSet<>());
        Role role = new Role();
        role.setName("SomeRole");

        when(userRepository.findUserById(user.getId())).thenReturn(Optional.of(user));
        when(roleService.findRoleByName(role.getName())).thenReturn(role);

        userService.addRoleToUser(user.getId(), role.getName());

        User expectedUser = new User();
        expectedUser.setId(user.getId());
        expectedUser.setRoles(Set.of(role));

        verify(userRepository, times(1)).save(expectedUser);
    }

    @Test
    @DisplayName(" метод должен отнять права у юзера и сохранить в БД ")
    void takeAwayUsersRights() {
        User user = new User();
        user.setId(1L);
        Role role = new Role();
        role.setName("SomeRole");
        Role role1 = new Role();
        role1.setName("SomeRole2");
        user.setRoles(new HashSet<>(Set.of(role, role1)));

        when(userRepository.findUserById(user.getId())).thenReturn(Optional.of(user));

        List<String> rolesForDeleting = new ArrayList<>();
        rolesForDeleting.add("SomeRole2");

        userService.takeAwayUsersRights(user.getId(), rolesForDeleting);

        User expectedUser = new User();
        expectedUser.setId(user.getId());
        expectedUser.setRoles(Set.of(role1));

        verify(userRepository, times(1)).save(expectedUser);

    }

}