package ru.netology.hw_rest_authorization.repository;

import org.springframework.stereotype.Repository;
import ru.netology.hw_rest_authorization.model.User;
import ru.netology.hw_rest_authorization.service.Authorities;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private ConcurrentHashMap<User, List<Authorities>> users = new ConcurrentHashMap<>();

    {
        users.put(new User("Ben", "qwerty"), Arrays.asList(Authorities.READ, Authorities.WRITE,
                Authorities.DELETE));
        users.put(new User("Sam", "1234"), List.of(Authorities.READ));
        users.put(new User("Dan", "wasd"), Arrays.asList(Authorities.READ, Authorities.WRITE));
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        User testUser = new User(user, password);
        for (Map.Entry<User, List<Authorities>> entry : users.entrySet()) {
            if (testUser.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
