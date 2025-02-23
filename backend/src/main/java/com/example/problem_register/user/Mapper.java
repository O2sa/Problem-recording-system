package com.example.problem_register.user;

import org.springframework.stereotype.Service;

@Service
public class Mapper {

    public User toUser(EditDto data) {
        User item = new User();
        item.setFullName(data.fullName());
        item.setUsername(data.username());
        item.setPassword(data.password());

        return item;
    }

    public ReadOtp toReadOtp(User model) {
        // var addByUser = model.getAddedBy();
        // var addBy = new ReadOtp(addByUser.getId(), addByUser.getFullName(), addByUser.getFullName(),
        //         addByUser.getAddDate(), null);
        System.out.println(model.toString());
        return new ReadOtp(model.getId(), model.getFullName(), model.getUsername(), model.getAddDate());
    }

}