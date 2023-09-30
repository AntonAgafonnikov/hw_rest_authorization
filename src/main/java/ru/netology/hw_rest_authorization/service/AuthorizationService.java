package ru.netology.hw_rest_authorization.service;

import org.springframework.stereotype.Service;
import ru.netology.hw_rest_authorization.exception.InvalidCredentials;
import ru.netology.hw_rest_authorization.exception.UnauthorizedUser;
import ru.netology.hw_rest_authorization.model.User;
import ru.netology.hw_rest_authorization.repository.UserRepository;

import java.util.List;

@Service
public class AuthorizationService {
    UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities() {
        return userRepository.getUserAuthorities();
    }
}
