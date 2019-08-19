package com.vlsu.demo.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;

@Entity
public class User {
    private int userId;
    @Size(min = 6, max = 50,message = "Длина логина от 6 до 50 символов")
    @NotNull(message = "Логин не может быть пустым")
    private String login;
    @Size(min = 6, max = 50,message = "Длина пароля от 6 до 50 символов")
    @NotNull(message = "Пароль не может быть пустым")
    private String password;
    private String role;
    private Admin adminByUserId;
    private Client clientByUserId;
    private Collection<Test> testsByUserId;

    public User(){

    }

    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "login", nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password", nullable = false)
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

        if (userId != user.userId) return false;
        if (!Objects.equals(login, user.login)) return false;
        if (!Objects.equals(password, user.password)) return false;
        return Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @OneToOne(mappedBy = "userByUserId")
    public Admin getAdminByUserId() {
        return adminByUserId;
    }

    public void setAdminByUserId(Admin adminByUserId) {
        this.adminByUserId = adminByUserId;
    }

    @OneToOne(mappedBy = "userByUserId")
    public Client getClientByUserId() {
        return clientByUserId;
    }

    public void setClientByUserId(Client clientByUserId) {
        this.clientByUserId = clientByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Test> getTestsByUserId() {
        return testsByUserId;
    }

    public void setTestsByUserId(Collection<Test> testsByUserId) {
        this.testsByUserId = testsByUserId;
    }
}

