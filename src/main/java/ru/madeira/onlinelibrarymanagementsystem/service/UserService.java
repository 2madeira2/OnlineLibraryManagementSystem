package ru.madeira.onlinelibrarymanagementsystem.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.madeira.onlinelibrarymanagementsystem.dto.UserDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Role;
import ru.madeira.onlinelibrarymanagementsystem.entity.User;
import ru.madeira.onlinelibrarymanagementsystem.exception.NonOriginalDataForSystemException;
import ru.madeira.onlinelibrarymanagementsystem.exception.UserAlreadyExistsInSystemException;
import ru.madeira.onlinelibrarymanagementsystem.exception.UserNotFoundException;
import ru.madeira.onlinelibrarymanagementsystem.mapper.UserMapper;
import ru.madeira.onlinelibrarymanagementsystem.repository.UserRepository;
import ru.madeira.onlinelibrarymanagementsystem.util.PasswordSecurityGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<Role> currentUserRoles = new HashSet<>();
        for(String role : user.getRoles()) {
            currentUserRoles.add(roleService.findRoleByName(role));
        }
        newUser.setRoles(currentUserRoles);
        String password = passwordSecurityGenerator.generatePassayPassword();
        newUser.setPassword(passwordEncoder.encode(password));
        mailSenderService.sendRegistrationMail(newUser.getEmail(), password);
        return userMapper.toDto(userRepository.save(newUser));
    }

    public UserDTO getUserById(Long id) {
         return userMapper.toDto(userRepository.findUserById(id).orElseThrow(UserNotFoundException::new));
    }

    public List<User> getAllUsersWhichIdInList(List<Long> idList) {
        return userRepository.findAllByIdIn(idList);
    }

    public User getUserByLogin(String login) {
        return userRepository.findUserByLogin(login).orElseThrow(UserNotFoundException::new);
    }

    public UserDTO editMyAccountData(String oldLogin, String newLogin, String email, String password) {
        User userForEdit = userRepository.findUserByLogin(oldLogin).orElseThrow(UserNotFoundException::new);
        if(userRepository.existsByLoginOrEmail(newLogin, email)) {
            throw new NonOriginalDataForSystemException();
        }
        userForEdit.setEmail(email);
        userForEdit.setLogin(newLogin);
        userForEdit.setPassword(passwordEncoder.encode(password));
        return userMapper.toDto(userRepository.save(userForEdit));
    }

}
