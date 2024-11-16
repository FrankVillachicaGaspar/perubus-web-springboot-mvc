package com.perubus.perubus.services;


import com.perubus.perubus.dtos.TripShortDto;

import java.util.List;

public interface ITripService {
    List<TripShortDto> getTop3Trips();

    List<TripShortDto> getAllTrips();

    TripShortDto getTripById(int id);
}
