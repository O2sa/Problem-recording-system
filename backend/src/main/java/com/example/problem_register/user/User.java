package com.example.problem_register.user;

import jakarta.persistence.*;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "re_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "user_name", nullable = false, length = 100, unique = true)
    private String username;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    @CreationTimestamp
    @Column(name = "add_date", updatable = false)
    private Date addDate;

    public User() {
    }

    public User(Long id, String username, String fullName, String password, Boolean status,
            Date addDate) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.status = status;
        this.addDate = addDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "Model [id=" + id + ", username=" + username + ", fullName=" + fullName + ", password=" + password
                + ", status=" + status + ", addDate=" + addDate + ", getId()=" + getId() + ", getUsername()="
                + getUsername() + ", getFullName()=" + getFullName() + ", getPassword()=" + getPassword()
                + ", getStatus()=" + getStatus() + ", getAddDate()=" + getAddDate() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    // Getters and Setters
}
