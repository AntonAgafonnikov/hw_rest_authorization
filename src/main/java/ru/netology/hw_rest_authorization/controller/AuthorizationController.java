package ru.netology.hw_rest_authorization.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.hw_rest_authorization.service.AuthorizationService;

@RestController
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    //Endpoint достпен всем
    @GetMapping("/home")
    public String welcomeToHome() {
        return "Welcome to HOME =)";
    }

    //Endpoint доступен только авторизованным пользователям с ролью WRITE:
    // ИМЯ  ПАРОЛЬ
    // user password
    // ben 1234
    @GetMapping("/create-post")
    @Secured({"ROLE_WRITE"})
    public String createPost() {
        return "Welcome, writer!";
    }

    //Endpoint доступен только авторизованным пользователям с ролью READ:
    // ИМЯ  ПАРОЛЬ
    // user password
    // sam  qwerty
    @GetMapping("/read-post")
    @RolesAllowed({"READ"})
    public String readPost() {
        return "Welcome, user! (There is a very interesting text here)";
    }

    //Endpoint доступен только авторизованным пользователям с любой из перечисленных ролей: WRITE/READ/DELETE
    // ИМЯ  ПАРОЛЬ
    // user password
    // ben 1234
    // sam  qwerty
    @GetMapping("/contacts")
    @PreAuthorize("hasAnyRole('WRITE', 'READ', 'DELETE')")
    public String openContact() {
        return "+7-123-543-44-66    Moscow, Dostoevskay st, 12";
    }

    //Endpoint доступен только авторизованным пользователям с любой из перечисленных ролей: WRITE/DELETE
    // ИМЯ  ПАРОЛЬ
    // user password
    // ben 1234
    //-----
    // Если сюда попробует зайти пользователь sam (qwerty), то код метода выполнится, в консоль выведется "10",
    // а в ответ на запрос вернётся - 403
    @GetMapping("/data-base-users")
    @PostAuthorize("hasAnyRole('WRITE', 'DELETE')")
    public String getDataBaseUsers() {
        int methodEntryDemonstration = 5;
        methodEntryDemonstration += 5;
        System.out.println(methodEntryDemonstration);
        return """
                1   Petya   pet@mail.ru   
                2   Leha   leha@mail.ru 
                3   Vasya   vasya@mail.ru
                """;
    }

    //Endpoint доступен только авторизованным пользователям с любой из перечисленных ролей: WRITE/READ/DELETE
    // ИМЯ  ПАРОЛЬ
    // user password
    // ben 1234
    // sam  qwerty
    //-----
    // Если в query-параметре передать имя, отличное от авторизованного пользователя, то всплывёт предупреждение,
    // иначе - вас персонально попреветствуют
    @GetMapping("/personal-greeting")
    public String getPersonalGreeting(@RequestParam String queryUser,
            @AuthenticationPrincipal User user) {
        String authorizedUser = user.getUsername();
        return queryUser.equals(authorizedUser)?
                ("Hello " + authorizedUser)
                :"WARNING! Another user is currently logged in";
    }
}

