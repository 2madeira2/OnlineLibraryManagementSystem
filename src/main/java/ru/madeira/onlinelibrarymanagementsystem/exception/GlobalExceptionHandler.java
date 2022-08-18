package ru.madeira.onlinelibrarymanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public String userNotFoundExceptionHandler() {
        return "User not found!";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistsInSystemException.class)
    public String userAlreadyExistsIsSystemExceptionHandler() {
        return "User already exists!";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GenreAlreadyExistsInSystemException.class)
    public String genreAlreadyExistsInSystemExceptionHandler() {
        return "Genre already exists!";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FreeBookCopiesNotFoundException.class)
    public String freeBookCopiesNotFoundExceptionHandler() {
        return "Free book copies not found!";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DebtsExistenceException.class})
    public String debtsExistenceExceptionHandler() {
        return "Book booking error. You have debts!";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NonOriginalDataForSystemException.class})
    public String nonOriginalDataForSystemExceptionHandler() {
        return "Non original data for your account!";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({GenreNotFoundException.class})
    public String genreNotFoundExceptionHandler() {
        return "Not found genre!";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RoleNotFoundException.class})
    public String roleNotFoundExceptionHandler() {
        return "Role not found!";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BookNotFoundException.class})
    public String bookNotFoundExceptionHandler() {
        return "Book not found!";
    }

}
