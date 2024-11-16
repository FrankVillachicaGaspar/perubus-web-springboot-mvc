package com.perubus.perubus.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CreateUserDto {

    @NotEmpty(message = "User email should not be empty")
    public String email;

    @NotEmpty(message = "User name should not be empty")
    public String name;

    @NotEmpty(message = "User lastname should not be empty")
    public String lastname;

    @NotEmpty(message = "User password should not be empty")
    public String password;
}
