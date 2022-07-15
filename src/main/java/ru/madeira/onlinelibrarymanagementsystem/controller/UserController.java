package ru.madeira.onlinelibrarymanagementsystem.controller;

import org.springframework.web.bind.annotation.*;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookDTO;
import ru.madeira.onlinelibrarymanagementsystem.dto.UserDTO;
import ru.madeira.onlinelibrarymanagementsystem.service.UserService;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/{readersTicketNumber}")
    public UserDTO findUserById(@PathVariable(value = "readersTicketNumber") Long readersTicketNumber) {
        return userService.findUserByReadersTicketNumber(readersTicketNumber);
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/readers")
    public List<UserDTO> getAllReaders() {
        return userService.getAllReaders();
    }

    @PostMapping("/users")
    public UserDTO addNewUser(@RequestBody UserDTO user) {
        return userService.addNewUser(user);
    }

}
