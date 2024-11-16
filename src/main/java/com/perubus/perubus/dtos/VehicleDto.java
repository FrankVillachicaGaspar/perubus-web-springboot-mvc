package com.perubus.perubus.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleDto {
    public int id;
    public String model;
    public String brand;
    public int year;
    public String type;
}
