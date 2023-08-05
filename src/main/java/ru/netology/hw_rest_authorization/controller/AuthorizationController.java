package ru.netology.hw_rest_authorization.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.hw_rest_authorization.model.User;
import ru.netology.hw_rest_authorization.service.Authorities;
import ru.netology.hw_rest_authorization.service.AuthorizationService;

import java.util.List;

@RestController
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@ModelAttribute("validUser") @Valid User user) {
        return service.getAuthorities(user);
    }
}


