package com.perubus.perubus.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {
    public int id;

    public String email;

    public String name;

    public String lastname;

    public LocalDateTime createdOn;

    public LocalDateTime updatedOn;
}
