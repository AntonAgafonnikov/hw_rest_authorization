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
//        String name = user.getName();
//        String password = user.getPassword();
//        if (isEmpty(name) || isEmpty(password)) {
//            throw new InvalidCredentials("User name or password is empty");
//        }
//        List<Authorities> userAuthorities = userRepository.getUserAuthorities(name, password);
//        if (isEmpty(userAuthorities)) {
//            throw new UnauthorizedUser("Unknown user " + user);
//        }
        return userRepository.getUserAuthorities();
    }
//
//    private boolean isEmpty(String str) {
//        return str == null || str.isEmpty();
//    }
//
//    private boolean isEmpty(List<?> str) {
//        return str == null || str.isEmpty();
//    }
}
