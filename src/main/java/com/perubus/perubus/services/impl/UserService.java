package com.perubus.perubus.services.impl;

import com.perubus.perubus.dtos.CreateUserDto;
import com.perubus.perubus.dtos.UserDto;
import com.perubus.perubus.models.User;
import com.perubus.perubus.repositories.IUserRepository;
import com.perubus.perubus.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "createdOn"));
        return users.stream().map(this::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public void saveUser(CreateUserDto userDto) {
        User user = User.builder()
                .name(userDto.name)
                .lastname(userDto.lastname)
                .email(userDto.email)
                .build();

        User userSaved = userRepository.save(user);
        mapToUserDto(userSaved);
    }

    @Override
    public UserDto findUserById(Long userId) {
        User user = userRepository.findById(userId).get();
        return mapToUserDto(user);

    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = userRepository.findById((long) userDto.id).get();
        user.setName(userDto.name);
        user.setLastname(userDto.lastname);
        user.setEmail(userDto.email);;
        user.setUpdatedOn(LocalDateTime.now());
        userRepository.save(user);
    }

    private User mapToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.id)
                .name(userDto.name)
                .lastname(userDto.lastname)
                .email(userDto.email)
                .build();
    }

    private UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .lastname(user.getLastname())
                .createdOn(user.getCreatedOn())
                .updatedOn(user.getUpdatedOn())
                .build();
    }
}
