package ru.madeira.onlinelibrarymanagementsystem.controller;

import org.springframework.web.bind.annotation.*;
import ru.madeira.onlinelibrarymanagementsystem.dto.UserDTO;
import ru.madeira.onlinelibrarymanagementsystem.dto.UserHistoryDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.User;
import ru.madeira.onlinelibrarymanagementsystem.service.BookCopyService;
//import ru.madeira.onlinelibrarymanagementsystem.service.MailSenderService;
import ru.madeira.onlinelibrarymanagementsystem.service.MailSenderService;
import ru.madeira.onlinelibrarymanagementsystem.service.UserHistoryService;
import ru.madeira.onlinelibrarymanagementsystem.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    private final UserHistoryService userHistoryService;

    private final BookCopyService bookCopyService;

    private final MailSenderService mailSenderService;

    public UserController(UserService userService, UserHistoryService userHistoryService, BookCopyService bookCopyService, MailSenderService mailSenderService) {
        this.userService = userService;
        this.userHistoryService = userHistoryService;
        this.bookCopyService = bookCopyService;
        this.mailSenderService = mailSenderService;
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users/{id}/history")
    public List<UserHistoryDTO> getUserHistoryByUserId(@PathVariable Long id) {
        return userHistoryService.getUserHistoryByUserId(id);
    }

    @GetMapping("/users/{id}/arrears")
    public List<UserHistoryDTO> getUserAppearsByUserId(@PathVariable Long id) {
        return userHistoryService.getUserAppearsByUserId(id);
    }

    @GetMapping("/readers")
    public List<UserDTO> getAllReaders() {
        return userService.getAllReaders();
    }

    @PostMapping("/users")
    public UserDTO createNewUser(@RequestBody UserDTO user) {
        return userService.createNewUser(user);
    }

    @GetMapping("/users/{id}/arrears/{bookCopyId}/release")
    public void releaseUsersAppearsBook(@PathVariable Long id, @PathVariable Long bookCopyId) {
        userHistoryService.releaseUsersBook(id, bookCopyId);
        bookCopyService.releaseBookCopy(bookCopyId);
    }

//    @PostMapping("/users/sendMailToUser")
//    public void sendMail(@RequestParam String to) {
//        mailSenderService.sendRegistrationMail(to, "password");
//    }

    @GetMapping("/myAccount")
    public User getMyAccountInformation(Principal principal) {
        return userService.getUserByLogin(principal.getName());
    }

    @PutMapping("/myAccount/editData")
    public UserDTO editMyAccountData(@RequestParam String login,
                                     @RequestParam String email,
                                     @RequestParam String password,
                                     Principal principal) {
        return userService.editMyAccountData(principal.getName(), login, email, password);
    }

    @PostMapping("/users/{id}/addRights")
    public void addRightsToUser(@PathVariable Long id, @RequestParam String role) {
        userService.addRoleToUser(id, role);
    }

    @DeleteMapping("/users/{id}/takeAwayRights")
    public UserDTO takeAwayUsersRights(@PathVariable Long id, @RequestParam List<String> roles) {
        return userService.takeAwayUsersRights(id, roles);
    }

}
