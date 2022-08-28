package ru.madeira.onlinelibrarymanagementsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.madeira.onlinelibrarymanagementsystem.dto.UserDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Role;
import ru.madeira.onlinelibrarymanagementsystem.entity.User;
import ru.madeira.onlinelibrarymanagementsystem.mapper.UserMapper;
import ru.madeira.onlinelibrarymanagementsystem.repository.UserRepository;
import ru.madeira.onlinelibrarymanagementsystem.util.PasswordSecurityGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final Long ID = 1L;

    @Mock
    private UserRepository userRepository;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Mock
    private RoleService roleService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private PasswordSecurityGenerator passwordSecurityGenerator;

    @Mock
    private MailSenderService mailSenderService;

    private UserService userService;

    @BeforeEach
    public void init() {
        this.userService = new UserService(userRepository, userMapper, roleService, passwordEncoder, passwordSecurityGenerator, mailSenderService);
    }

    @Test
    void getUserByIdTest() {
        final User expectedUser = mock(User.class);
        when(userRepository.findUserById(ID)).thenReturn(Optional.of(expectedUser));

        final UserDTO actualUser = userService.getUserById(ID);

        assertNotNull(actualUser);
        assertEquals(userMapper.toDto(expectedUser), actualUser);
        verify(userRepository).findUserById(ID);
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
}