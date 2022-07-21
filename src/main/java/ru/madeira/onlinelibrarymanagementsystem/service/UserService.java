package ru.madeira.onlinelibrarymanagementsystem.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.madeira.onlinelibrarymanagementsystem.dto.UserDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Role;
import ru.madeira.onlinelibrarymanagementsystem.entity.User;
import ru.madeira.onlinelibrarymanagementsystem.exception.UserAlreadyExistsInSystemException;
import ru.madeira.onlinelibrarymanagementsystem.exception.UserNotFoundException;
import ru.madeira.onlinelibrarymanagementsystem.mapper.UserMapper;
import ru.madeira.onlinelibrarymanagementsystem.repository.UserRepository;
import ru.madeira.onlinelibrarymanagementsystem.util.PasswordSecurityGenerator;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final PasswordSecurityGenerator passwordSecurityGenerator;
    private final MailSenderService mailSenderService;

    public UserService(UserRepository userRepository, UserMapper userMapper, RoleService roleService, PasswordEncoder passwordEncoder, PasswordSecurityGenerator passwordSecurityGenerator, MailSenderService mailSenderService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.passwordSecurityGenerator = passwordSecurityGenerator;
        this.mailSenderService = mailSenderService;
    }

    public List<UserDTO> getAllUsers() {
        return userMapper.toDto(userRepository.findAll());
    }

    public List<UserDTO> getAllReaders() {
        return userMapper.toDto(userRepository.findAllByRolesName("READER"));
    }

    public UserDTO findUserByReadersTicketNumber(Long readersTicketNumber) {
        return userMapper.toDto(userRepository.findUserByReadersTicketNumber(readersTicketNumber).orElseThrow(UserNotFoundException::new));
    }

    public UserDTO createNewUser(UserDTO user) {
        if(userRepository.existsByLoginOrReadersTicketNumber(user.getLogin(), user.getReadersTicketNumber())) {
            throw new UserAlreadyExistsInSystemException();
        }
        User newUser = userMapper.toUser(user);
        String password = passwordSecurityGenerator.generatePassayPassword();
        newUser.setPassword(passwordEncoder.encode(password));
        mailSenderService.sendRegistrationMail(newUser.getEmail(), password);
        return userMapper.toDto(userRepository.save(newUser));
    }

    public UserDTO getUserById(Long id) {
         return userMapper.toDto(userRepository.findUserById(id).orElseThrow(UserNotFoundException::new));
    }
}
