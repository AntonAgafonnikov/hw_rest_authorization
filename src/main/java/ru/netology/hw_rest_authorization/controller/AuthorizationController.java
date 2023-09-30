package ru.netology.hw_rest_authorization.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.hw_rest_authorization.security.WebSecurityConfig;
import ru.netology.hw_rest_authorization.service.Authorities;
import ru.netology.hw_rest_authorization.service.AuthorizationService;

@RestController
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/home")
    public String welcomeToHome() {
        return "Welcome to HOME =)";
    }

    @GetMapping("/create-post")
    public String createPost() {
        return "Welcome, writer!";
    }

    @GetMapping("/read-post")
    public String readPost() {
        return "Welcome, user! (There is a very interesting text here)";
    }
    @GetMapping("/contacts")
    public String openContact() {
        return "+7-123-543-44-66    Moscow, Dostoevskay st, 12";
    }

    @GetMapping("/data-base-users")
    public String getDataBaseUsers() {
        return """
                1   Petya   pet@mail.ru
                
                2   Leha   leha@mail.ru
                
                3   Vasya   vasya@mail.ru
                """;
    }

    @GetMapping("/personal-greeting")
    @PreAuthorize("#username = authentication.principal.username")
    public String getPersonalGreeting(String username) {
        return username;
    }

}

/*
- один из методов возвращает значения только для пользователей с ролью "READ" (используйте @Secured);
- один из методов возвращает значения только для пользователей с ролью "WRITE" (используйте @RolesAllowed);
- один из методов возвращает значения, если у пользователя есть хотя бы одна из ролей из "WRITE", "DELETE"
    (используйте pre/post аннотации);
- один из методов, который принимает в качестве query-параметра имя пользователя (username), должен
    возвращает значения, только если у пользователя username совпадает с именем пользователя в вашем объекте
    Authentication, который Spring security сохраняет в SecurityContextHolder после успешной аутентификации.
 */


