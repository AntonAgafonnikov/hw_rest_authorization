package ru.netology.hw_rest_authorization.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class User {
    @NotEmpty(message = "A user can't be create without name!")
    @Size(min = 2, max = 20, message = "A name size should be from 2 to 20 characters!")
    private String name;
    @NotEmpty(message = "A user can't be create without password!")
    @Size(min = 4, max = 10, message = "A password size should be from 4 to 10 characters!")
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
