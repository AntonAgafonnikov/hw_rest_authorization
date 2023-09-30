package ru.netology.hw_rest_authorization.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.hw_rest_authorization.service.AuthorizationService;

@RestController
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

//    @GetMapping("/authorities")
//    public List<Authorities> getAuthorities() {
//        return service.getAuthorities();
//    }

    @GetMapping("/home")
    public String welcomeToHome() {
        return "Welcome to HOME =)";
    }

    @GetMapping("/secret-info")
    public String getTopSecret() {
        return "Актеры, которые озвучивали Микки Мауса и Минни Маус, были женаты в реальной жизни.";
    }
}


