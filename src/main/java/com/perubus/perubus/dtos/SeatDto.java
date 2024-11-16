package com.perubus.perubus.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SeatDto {
    public int id;
    public int number;
    public LocalDateTime blockTime;
}
