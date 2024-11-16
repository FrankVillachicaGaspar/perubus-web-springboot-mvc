package com.perubus.perubus.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TripShortDto {
    public int id;

    public String description;

    public String destination;

    public String imageUrl;

    public LocalDateTime departureTime;

    public LocalDateTime arrivalTime;

    public double price;
}
