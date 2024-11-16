package com.perubus.perubus.services.impl;

import com.perubus.perubus.dtos.SeatDto;
import com.perubus.perubus.models.Seat;
import com.perubus.perubus.repositories.ISeatRepository;
import com.perubus.perubus.services.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService implements ISeatService {
    private final ISeatRepository seatRepository;

    @Autowired
    public SeatService(ISeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<SeatDto> getSeatsByVehicleId(int vehicleId) {
        List<Seat> seatList = seatRepository.findAllByVehicleId(vehicleId);

        return seatList.stream().sorted(Comparator.comparing(Seat::getNumber)).map(seat ->
                SeatDto.builder()
                        .id(seat.getId())
                        .number(seat.getNumber())
                        .blockTime(seat.getBlockTime())
                        .build()
        ).collect(Collectors.toList());
    }
}
