package com.davidorellana.jwtloginauthspringsecurity.user.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Document(collection = "user_collection")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String fullName;
    private Integer age;
    @Indexed(unique = true)
    private String email;
    private String password;

    public User() { }

    public User(UserDto userDto) {
        this.updateUserCollection(userDto);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void updateUserCollection(UserDto userDto) {
        this.fullName = userDto.getFullName();
        this.age = userDto.getAge();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
    }
}
