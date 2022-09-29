package ru.madeira.onlinelibrarymanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public String userNotFoundExceptionHandler() {
        return "User not found!";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistsInSystemException.class)
    public String userAlreadyExistsIsSystemExceptionHandler() {
        return "User already exists!";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GenreAlreadyExistsInSystemException.class)
    public String genreAlreadyExistsInSystemExceptionHandler() {
        return "Genre already exists!";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FreeBookCopiesNotFoundException.class)
    public String freeBookCopiesNotFoundExceptionHandler() {
        return "Free book copies not found!";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DebtsExistenceException.class})
    public String debtsExistenceExceptionHandler() {
        return "Book booking error. You have debts!";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NonOriginalDataForSystemException.class})
    public String nonOriginalDataForSystemExceptionHandler() {
        return "Non original data for your account!";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({GenreNotFoundException.class})
    public String genreNotFoundExceptionHandler() {
        return "Not found genre!";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RoleNotFoundException.class})
    public String roleNotFoundExceptionHandler() {
        return "Role not found!";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BookNotFoundException.class})
    public String bookNotFoundExceptionHandler() {
        return "Book not found!";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({AuthorNotFoundException.class})
    public String authorNotFoundExceptionHandler() {
        return "Author not found!";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BookCopyNotExistsException.class)
    public String bookCopyNotExistsExceptionHandler() {
        return "Book copy doesn't exists!";
    }

}
