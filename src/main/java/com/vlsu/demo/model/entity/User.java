package com.vlsu.demo.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class User {
    private int userId;
    @Size(min = 6, max = 50, message = "Длина логина от 6 до 50 символов")
    @NotNull(message = "Логин не может быть пустым")
    private String login;
    @Size(min = 6, max = 50, message = "Длина пароля от 6 до 50 символов")
    @NotNull(message = "Пароль не может быть пустым")
    private String password;
    private String role;
    private Client clientByUserId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == (user.userId) &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                role.equals(user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, password, role);
    }

    @OneToOne(mappedBy = "userByUserId")
    public Client getClientByUserId() {
        return clientByUserId;
    }

    public void setClientByUserId(Client clientByUserId) {
        this.clientByUserId = clientByUserId;
    }

    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User() {
    }
}
