package com.perubus.perubus.services;

import com.perubus.perubus.dtos.CreateUserDto;
import com.perubus.perubus.dtos.UserDto;

import java.util.List;

public interface IUserService {
    List<UserDto> findAllUsers();

    void saveUser(CreateUserDto userDto);

    UserDto findUserById(Long userId);

    void updateUser(UserDto userDto);
}
