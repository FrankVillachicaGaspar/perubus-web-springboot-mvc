package com.perubus.perubus.services;


import com.perubus.perubus.dtos.SeatDto;

import java.util.List;

public interface ISeatService {
    List<SeatDto> getSeatsByVehicleId(int vehicleId);
}
