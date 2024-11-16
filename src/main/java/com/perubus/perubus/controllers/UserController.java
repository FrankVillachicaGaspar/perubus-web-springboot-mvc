package com.perubus.perubus.controllers;

import com.perubus.perubus.dtos.CreateUserDto;
import com.perubus.perubus.dtos.UserDto;
import com.perubus.perubus.models.User;
import com.perubus.perubus.services.impl.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "user/users-list";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "user/users-create";
    }

    @PostMapping("/users/new")
    public String saveUser(@Valid @ModelAttribute("user") CreateUserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/users-create";
        }
        userService.saveUser(userDto);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}/edit")
    public String editUser(@PathVariable Long userId, Model model) {
        UserDto user = userService.findUserById(userId);
        model.addAttribute("user", user);
        return "user/users-edit";
    }

    @PostMapping("/users/{userId}/edit")
    public String updateUser(@PathVariable Long userId, @Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/users-edit";
        }
        user.setId(Math.toIntExact(userId));
        userService.updateUser(user);
        return "redirect:/users";
    }
}
