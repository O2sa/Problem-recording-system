package com.example.problem_register.user;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository repository;
    private final Mapper mapper;

    public UserService(UserRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public ReadOtp getById(Long id) {
        return mapper.toReadOtp(repository.getById(id));
    }

    public ReadOtp create(EditDto dto) {

        var item = repository.save(mapper.toUser(dto));
        return mapper.toReadOtp(item);
    }

    public ReadOtp update(Long id, EditDto dto) {
        User item = repository.getById(id);
        item.setFullName(dto.fullName());
        // item.setUsername(dto.username());
        repository.save(item);
        return mapper.toReadOtp(item);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}