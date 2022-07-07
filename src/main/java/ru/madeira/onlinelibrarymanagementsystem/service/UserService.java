package ru.madeira.onlinelibrarymanagementsystem.service;

import org.springframework.stereotype.Service;
import ru.madeira.onlinelibrarymanagementsystem.dto.UserDTO;
import ru.madeira.onlinelibrarymanagementsystem.exception.UserNotFoundException;
import ru.madeira.onlinelibrarymanagementsystem.mapper.MyUserMapper;
import ru.madeira.onlinelibrarymanagementsystem.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final MyUserMapper userMapper;

    public UserService(UserRepository userRepository, MyUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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
}
