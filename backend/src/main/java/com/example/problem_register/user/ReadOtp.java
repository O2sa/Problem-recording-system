package com.example.problem_register.user;

import java.util.Date;

public record ReadOtp(
      Long id,
      String fullName, String username, Date addDate) {

}