package com.example.problem_register.user;


import com.example.problem_register.base.validators.UniqueField;

import jakarta.validation.constraints.NotEmpty;

public record EditDto(
        @NotEmpty(message = "this filed must not be empty") String fullName,
        @NotEmpty(message = "this filed must not be empty") @UniqueField(entityClass = User.class, fieldName = "username") String username,
        @NotEmpty(message = "this filed must not be empty") String password) {

}